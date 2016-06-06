
	function pay(movie_id,price){
		
		if(movie_id && price){
			$("#J_movieid").val(movie_id);
			$("#J_price").val(price);
			$("#J_sub_ali").click();
		}
		
	}
	
	
	function showtoggle(id){
		if(id){
			$("#donate_"+id).toggle();
		}
	}