$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

new Vue({
        el: 'body',

  data: {
        type: 'article',
        notValidId: false,
  },
  
  props: ['originalid'],

  methods: {
        typeChange: function(){
            $('[data-toggle="tooltip"]').tooltip();
        },
        
        idChange: function(){
            if(typeof this.id !== 'undefined' && this.id !== ''){
                this.$http.get('/checkId/' + this.id).then(function (response) {
                    if(typeof response.data !== 'object' || this.originalid === this.id){
                        this.notValidId = false;
                    } else {
                        this.notValidId = true;
                    }
                }.bind(this), function (response) {
                    console.log("--- Id Check Failed");
                    console.log(response);
                });
            }
        }
  }
});