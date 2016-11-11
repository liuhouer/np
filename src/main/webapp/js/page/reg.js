function em(email){
	
	   var Regex = /^(?:\w+\.?)*\w+@(?:\w+\.)*\w+$/;            

	   if (Regex.test(email)){                

		return true;
		
	   }else{
		   return false;
	   }        
		
	}

$(document).ready(function() {


	if( $('#formSubmit').val().length<6){
		 
		$('#formSubmit').attr('disabled',true);
    }
	
  
   $('#signupForm').on('keyup', '#newPassword', function(event) {
	   if($('#newPassword').val().length>=6 && em($('#newAccount').val()))
	          $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );
      else
        $('#formSubmit').attr('disabled',true);
    }); 
   
   $("#newAccount").change(function(){
	   if($('#newPassword').val().length>=6 && em($('#newAccount').val()))
	          $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );
	   else
		   $('#formSubmit').attr('disabled',true);
	});
   
  
});