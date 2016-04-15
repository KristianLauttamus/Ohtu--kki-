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
        query: ''
    },

    ready: function(){
        this.getItems();
    },

    methods: {
        search: function(clearQuery){
            var spotOn = [];
            var almost = [];

            for(var i = 0; i < this.items.length; i++){
                for(var key in this.items[i]){
                    if(this.items[i][key] === this.query){
                        spotOn.push(this.items[i]);
                        break;
                    } else if(this.items[i][key].indexOf(this.query) > -1){
                        var tempItem = clone(this.items[i]);
                        
                        var index = tempItem[key].indexOf(this.query);
                        var endIndex = index + this.query.length;
                        var value = tempItem[key];
                        var output = [value.slice(0, index), '<b>', value.slice(index, endIndex), '</b>', value.slice(endIndex)].join('');
                        
                        
                        tempItem[key] = output;
                        
                        almost.push(tempItem);
                        break;
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
            var print = '';
            for(var key in citation){
                if(key !== 'id'){
                    print += key + ': ' + citation[key] + " ";
                }
            }
            
            return print;
        },
        
        remove: function(item){
            item.id.replace('<b>', '');
            item.id.replace('</b>', '');
            this.$http.post(item.id + '/delete').then(function (response) {
                this.items.$remove(item);
                this.listItems.$remove(item);
            }.bind(this), function (response) {
                console.log("--- Deletion Failed");
                console.log(response);
            });
        }
    },
    
    computed: {
        notEmptyAndHasQuery: function(){
            if(this.items.length === 0){
                return false;
            }
            
            return this.query !== '';
        }
    }
});