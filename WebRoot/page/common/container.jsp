<%@ page pageEncoding="UTF-8"%>

<div class="container">
	<div class="mainfoot center">
		<p style="color: #999">	
			©2014 布.词 &nbsp; &nbsp; <a href="http://northpark.cn"
				title="博客">博客blog</a> &nbsp; &nbsp; 联系布.词：654714226@qq.com &nbsp; &nbsp; 
				<a href="/cm/xbjt" title="小布静听">小布静听</a> &nbsp; &nbsp; 
				<!-- <span id="qqLoginBtn"></span>
				<script type="text/javascript">
					QC.Login({
						btnId : "qqLoginBtn" //插入按钮的节点id
					});
				</script> -->
		</p>
	</div>
</div>

<script src="/js/jquery-1.11.0.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/main2.js"></script>
    
<script>
$(function(){
	
		var tabs = "${tabs}";
		//set样式
		$("#J_tabs").find('li').each(function(){
			if(tabs==$(this).attr('cname')){
				//alert(tabs);
				//$(this).attr("class","active");
				$(this).addClass('active');//添加样式，样式名为className
			}
			
		});
});



</script>
