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
                        almost.push(this.items[i]);
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
                print += key + ': ' + citation[key];
            }
            
            return print;
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