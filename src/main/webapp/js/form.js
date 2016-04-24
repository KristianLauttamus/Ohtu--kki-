$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

new Vue({
        el: 'body',

  data: {
        type: 'article',
        notValidId: false,
  },
  
  props: ['originalId'],

  methods: {
        typeChange: function(){
            $('[data-toggle="tooltip"]').tooltip();
        },
        
        idChange: function(){
            if(typeof this.id !== 'undefined' && this.id !== this.originalId){
                this.$http.get('/checkId/' + this.id).then(function (response) {
                    if(typeof response.data !== 'object'){
                        this.notValidId = false;
                        console.log(typeof response.data);
                    } else {
                        this.notValidId = true;
                    }
                }.bind(this), function (response) {
                    console.log("--- Deletion Failed");
                    console.log(response);
                });
            }
        }
  }
});