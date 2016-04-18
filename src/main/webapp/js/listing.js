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

            for(var i = 0; i < this.items.length; i++){
                var add = false;
                var wasSpotOn = false;
                var tempItem = clone(this.items[i]);
                
                for(var key in tempItem){
                    if(tempItem[key] === this.query){
                        spotOn.push(this.items[i]);
                        wasSpotOn = true;
                        break;
                    } else if(tempItem[key].indexOf(this.query) > -1){
                        var index = tempItem[key].indexOf(this.query);
                        var endIndex = index + this.query.length;
                        var value = tempItem[key];
                        var output = [value.slice(0, index), '<b>', value.slice(index, endIndex), '</b>', value.slice(endIndex)].join('');

                        tempItem[key] = output;
                        
                        add = true;
                    }
                }
                
                if(add && !wasSpotOn){
                    almost.push(tempItem);
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
            var print = '<table class="table table-condensed" style="margin:0px;">';
            var header = '<thead><tr>';
            var body = '<tbody><tr>';
            
            for(var key in citation){
                if(key !== 'id' && key !== 'citationType' && key !== 'requiredFields' && key !== 'optionalFields' && citation[key] !== ''){
                    header += '<th>'+key+'</th>';
                    body += '<td>' + citation[key] + '</td>';
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