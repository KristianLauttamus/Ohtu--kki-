$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

new Vue({
        el: 'body',

  data: {
        type: 'article'
  },

  methods: {
        typeChange: function(){
            

            $('[data-toggle="tooltip"]').tooltip();
        },
        
        inputValueChanged: function(button){
            if(this.type === 'book'){
                
            } else if(this.type === 'inbook'){

            }
        }
  }
});