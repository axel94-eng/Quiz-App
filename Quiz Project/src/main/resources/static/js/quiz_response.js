
         $(document).ready(function(){
             $(".radio-container").children('input:radio').each(function() {
                 if($(this).is(':checked')) {
                    console.log('TEST' + $(this).next().css('background-color'));

                    if($(this).next().css('background-color') == 'rgba(0, 0, 0, 0)'){
                         $(this).next().css('background-color', 'red');
                          console.log('red');
                    }
                 } else {
                     $(this).attr('disabled', true);
                 }
            });
         });
