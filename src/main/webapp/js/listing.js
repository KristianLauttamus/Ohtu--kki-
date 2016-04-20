function clone(obj) {
    if (null == obj || "object" != typeof obj) return obj;
    var copy = obj.constructor();
    for (var attr in obj) {
        if (obj.hasOwnProperty(attr)) copy[attr] = obj[attr];
    }
    return copy;
}

new Vue({
    el: '#citation-table',
    
    data: {
        items: [],
        listItems: [],
        query: '',
        modal: false,
        filename: ''
    },

    ready: function(){
        this.getItems();
    },

    methods: {
        search: function(clearQuery){
            var spotOn = [];
            var almost = [];
            
            if(this.query === ''){
                this.showAll();
                return;
            }
            
            var splitQuery = this.query.split(/ ?[;] ?/);;
            
            console.log(this.items);
            
            for(var i = 0; i < this.items.length; i++){
                var ignore = false;
                var addAlmost = false;
                var add = false;
                
                var item = clone(this.items[i]);
                delete item["optionalFields"];
                delete item["requiredFields"];
                
                for(var q = 0; q < splitQuery.length; q++){
                    var query = splitQuery[q];
                    
                    if(query !== ''){
                    
                        if(query.length > 1){
                            var trueQuery = query.slice(1, query.length);
                        } else {
                            var trueQuery = query;
                        }
                        
                        var foundKeyForDefault = false;
                        var ignoreForDefault = false;

                        checkKeys:
                        for(var key in item){
                            switch(true){
                                case query.indexOf("@") === 0: // Type
                                    if(key === "citationType"){
                                        if(item[key] === trueQuery){
                                            add = true;
                                        } else {
                                            ignore = true;
                                            break checkKeys;
                                        }
                                    }

                                    break;

                                case query.indexOf("!") === 0: // Negation
                                    if(this.items[i][key].indexOf(trueQuery) < 0){
                                        addAlmost = true;
                                    } else {
                                        ignore = true;
                                        break checkKeys;
                                    }

                                    break;
                                    
                                case query.indexOf("#") === 0: // Negation
                                    if(key === trueQuery){
                                        add = true;
                                    } else {
                                        ignore = true;
                                        break checkKeys;
                                    }

                                    break;

                                default:
                                    if(this.items[i][key] === query){
                                        add = true;
                                        foundKeyForDefault = true;
                                    } else if(this.items[i][key].indexOf(query) > -1) {
                                        var index = this.items[i][key].indexOf(query);
                                        var endIndex = index + query.length;
                                        var value = this.items[i][key];
                                        var output = [value.slice(0, index), '<b>', value.slice(index, endIndex), '</b>', value.slice(endIndex)].join('');
                                        
                                        item[key] = output;
                                        
                                        addAlmost = true;
                                        foundKeyForDefault = true;
                                    } else {
                                        if(foundKeyForDefault){
                                            ignoreForDefault = false;
                                        } else {
                                            item[key] = this.items[i][key];
                                            ignoreForDefault = true;
                                        }
                                    }

                                    break;
                            }
                        }
                        
                        if(ignoreForDefault && !foundKeyForDefault){
                            ignore = true;
                        }
                    }
                }
                
                if(!ignore){
                    if(add){
                        spotOn.push(item);
                    } else if(addAlmost){
                        almost.push(item);
                    }
                }
            }
            
            if(spotOn.length > 0 && almost.length > 0){
                this.listItems = spotOn.concat(almost);
            } else if(spotOn.length > 0){
                this.listItems = spotOn;
            } else if(almost.length > 0){
                this.listItems = almost;
            } else {
                this.listItems = [];
            }
            
            if(typeof clearQuery !== 'undefined' && clearQuery === true){
                this.query = '';
            }
        },
        
        getItems: function(){
            this.$http({url: '/all', method: 'GET'}).then(function (response) {
                this.items = response.data;
                this.listItems = response.data;
            }.bind(this), function (response) {
                // error callback
            });
        },
        
        showAll: function(){
            this.listItems = this.items;
            this.query = '';
        },
        
        printCitation: function(citation){
            var print = '<table class="table" style="margin:0px;background: none;">';
            var header = '<thead><tr>';
            var body = '<tbody><tr>';
            
            for(var key in citation){
                if(key !== 'id' && key !== 'citationType' && key !== 'requiredFields' && key !== 'optionalFields' && citation[key] !== ''){
                    header += '<th style="text-align:center;">'+key+'</th>';
                    body += '<td style="text-align:center;">' + citation[key] + '</td>';
                }
            }
            
            body += '</tr></tbody>';
            header += '</tr></thead>';
            
            print += header + body;
            
            return print + '</table>';
        },
        
        remove: function(item){
            var oldItem = clone(item);
            
            item.id.replace('<b>', '');
            item.id.replace('</b>', '');
            this.$http.post(item.id + '/delete').then(function (response) {
                this.items.$remove(item);
                this.listItems.$remove(oldItem);
            }.bind(this), function (response) {
                console.log("--- Deletion Failed");
                console.log(response);
            });
        }
    },
    
    computed: {
        isEmptyAndHasQuery: function(){
            return this.listItems.length === 0 && this.query !== '';
        },
        
        notEmptyAndHasQuery: function(){
            return this.items.length > 0 && this.query !== '';
        },
        
        notEmptyAndNoQuery: function(){
            return this.items.length > 0 && this.query === '';
        },
        
        downloadLink: function(){
            if(this.filename === ''){
                return 'download';
            }
            
            return 'download?filename=' + this.filename;
        }
    },
    
    components: {
        modal: VueStrap.modal
    }
});