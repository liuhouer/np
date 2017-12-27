$(function(){
	//加载love;

	 $.ajax({
			url:"/dash/getLove",
			type:"post",
			success:function(data){
				if(data){
					$("#J_container_love").prepend(data);
					
					//绑定动态事件
					 $("div.blog-post").hover(
							    function() {
							        $(this).find("div.content-hide").slideToggle("fast");
							    },
							    function() {
							        $(this).find("div.content-hide").slideToggle("fast");
							    }
					);
				}			
			}
		});
	 
	 
	
	
	//加载suisui
	$.ajax({
			url:"/dash/getNote",
			type:"post",
			success:function(data){
				if(data){
					$("#J_container_note").prepend(data);
					
					//激活动作
					 $('.flexslider').flexslider({
							prevText: '',
							nextText: ''
						});

					  $('.testimonails-slider').flexslider({
					    animation: 'slide',
					    slideshowSpeed: 5000,
					    prevText: '',
					    nextText: '',
					    controlNav: false
					  });
				}			
			}
		});
	
	//加载romeo
	$.ajax({
			url:"/dash/getRomeo",
			type:"post",
			success:function(data){
				if(data){
					$("#J_container_romeo").prepend(data);
				}			
			}
		});
	
	//加载movies
	$.ajax({
		url:"/dash/getMovies",
		type:"post",
		success:function(data){
			if(data){
				$("#J_container_movies").prepend(data);
				
				/*电影轮播*/

					//首页切换效果
						$("#count1").dayuwscroll({
							parent_ele: '#wrapBox1',
							list_btn: '#tabT04',
							pre_btn: '#left1',
							next_btn: '#right1',
							path: 'left',
							auto: true,
							time: 3000,
							num: 5,
							gd_num: 5,
							waite_time: 1000

					})
				
			/*电影轮播end*/	
			}			
		}
	});
});




$(function(){

//	Instantiate MixItUp:

	$('#Container').mixItUp();



	$(document).ready(function() {
		$(".fancybox").fancybox();
	});

});


