<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0030)myself.jsp -->
<html lang="zh-CN"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<meta http-equiv="Content-Language" content="zh-CN">

<meta name="author" content="www.qinco.net">
<meta name="robots" content="index,follow,archive">
<link rel="shortcut icon" href="img/favicon.png">
<title>小布静听构建</title>
<meta name="description" content="${user.username}生命中的最爱: 布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="page/common/common.jsp"%>


</head>

<body style="">
	<%@ include file="/page/common/navigation.jsp"%>

    	<div class="clearfix mainhead " style="background:#f4f3f1;">
		<div class="container">
			<div class="row margin-b20 margin-t20">
				<div class="col-sm-6 col-sm-offset-3 margin-b20 margin-t20">
					<div class="row margin-b20 margin-t20">
<p>				
<br>原创软件：小布静听 2014-7-10更新到1.9.0.1
<br>最近几天很闲，把之前的不完善的地方都重新编写了。主要如下：
<br>添加文件夹，会扫描目录
<br>全面解析lrc歌词以及桌面歌词的一些操作逻辑
<br>重构文件结构，去除冗余代码
<br>tag信息的部分完善
<br>添加/编辑歌词的操作逻辑调整
<br>部分细节调整
<br>下载地址： http://pan.baidu.com/s/1c01Vp5I

</p>
<br>最新的这版我用了2天，感觉很好用，听本地歌曲，搜网上歌词。。大家可以试试 
<br>////////////////////////////////////////////////////////////////历史更新/////////////////////////////////////////
<br>小步静听  主要构建历史
<br>
<br>第一个版本，比较简陋的界面和功能
<br>下载地址： http://pan.baidu.com/share/link?shareid=85286&uk=3860164064
<br>
<br>第二个版本，初步写出了歌词秀同步功能和换肤功能，（将*.skn文件放在应用程序同目录下即可切换皮肤）
<br>下载地址：http://pan.baidu.com/share/link?shareid=85287&uk=3860164064 
<br>
<br>第三个版本，用时钟控制实现了磁性窗体，继续研究... 
<br>下载地址：http://pan.baidu.com/share/link?shareid=85288&uk=3860164064  
<br>
<br>第四个版本，调用bass.dll，完全颠覆了以前的算法，界面漂亮不少，能自己调节均衡器，bug很少，功能有待增加...
<br>
<br>下载地址：http://pan.baidu.com/share/link?shareid=85288&uk=3860164064 
<br>
<br>第五个版本，磁性窗体控件化，win7可以完美体现.
<br>下载地址：http://pan.baidu.com/share/link?shareid=85290&uk=3860164064 
<br>
<br>
<br>第六个版本，实现基本所有功能，就不截图了，
<br>下载地址：http://pan.baidu.com/share/link?shareid=85291&uk=3860164064 
<br>
<br>第七个版本，自动记录播放列表并加载，还有好多新功能，换了皮肤编写控件，写了2个皮肤，没记录位置替换然后那样太墨迹了，生成2个程序，凭自己喜好，下载使用吧  
<br>古铜：http://pan.baidu.com/share/link?shareid=85293&uk=3860164064  
<br>winamp版：http://pan.baidu.com/share/link?shareid=85292&uk=3860164064  
<br>
<br>
<br>
<br>
<br>1.7：http://pan.baidu.com/share/link?shareid=85302&uk=3860164064 
<br>
<br>1.7.1：http://pan.baidu.com/share/link?shareid=85304&uk=3860164064 
<br>
<br>1.7.2：http://pan.baidu.com/share/link?shareid=85305&uk=3860164064 
<br>
<br>
<br>最后，有几个皮肤文件，下载后解压，2-6个版本可以用，放到他们执行程序的目录即可切换皮肤了...
<br>下载皮肤：http://pan.baidu.com/share/link?shareid=85298&uk=3860164064 
<br>
<br>没了...........
<br>
<br>
<br>
<br>小步静听更新到1.8，（1.8--1.8.2三种桌面歌词效果），win7 下显示效果还不错，
<br>修正bug，欢迎下载试用！（XP效果一般），
<br>经我测试，bug比1.7少了好多，能正常使用，歌词部分正在努力！
<br>原来delphi写了磁性窗体的代码，在XP下仍然很鸡肋，win7效果很好！
<br>2.添加类似qq的靠近侧边自动隐藏伸缩功能
<br>种桌面歌词风格，随你喜好
<br>.所有版本都是下载后解压再用！
<br>.1.8.2版本桌面歌词我添加了显示图片，那个文件夹下必须有张“bruce.png”，你可以随意使用自己喜欢的图片，但是名称必须一致，否则会提示“out of memory...”
<br>.所有版本都是单文件可执行程序，没有调用dll，一大堆人家老外开发的东西，调用后，多累赘！
<br>没了....
<br>
<br>
<br>下载地址：
<br>1.8：   http://pan.baidu.com/share/link?shareid=85307&uk=3860164064 
<br>1.8.1：  http://pan.baidu.com/share/link?shareid=85308&uk=3860164064 
<br>1.8.2：   http://pan.baidu.com/share/link?shareid=85308&uk=3860164064 
<br>
<br>//--------------------------------------------------------------------------------------------------------------------------
<br>放假了，闲着没事又研究研究，更新一些功能---------------2012/10/1   11:55
<br>加入读取id3标签，可以自己定义艺术家、专辑、年代、、、、一键定义，特简易。
<br>2.加入识别操作系统版本，进而来控制窗体的磁性效果。
<br>加入桌面歌词保持最前端功能，我不知道千千静听的实现算法，自己想当然用时钟控制的，反正实现了就行，以后有新的想法再修改吧--
<br>4.修正一些bug5.依然未解决的：桌面歌词偶尔空指针问题，这个比较困难，暂时还没想到好的解决办法.                        
<br>不要多开，只运行一个。否则读取播放列表文件时，会提示资源正在占用。
<br>
<br>下载：1.8.3：  http://pan.baidu.com/share/link?shareid=85311&uk=3860164064 
<br>
<br>//--------------------------------------------------------------------------------------------------------
<br>刚考完试了，更新一些bug，---------------2012/10/23   16:59
<br>1.修正标签修改后不立即显示的bug
<br>2.修改桌面歌词滚动的算法，
<br>3.添加一些小优化
<br>下载：1.8.4 ：http://pan.baidu.com/share/link?shareid=89446&uk=3860164064
<br>
<br>
<br>
<br>
<br>
<br>Blythe-Player-build-history
<br>a personal MP3 player ，build by bruce （series），if you want to try ，first change your default language：Simple Chinese，enjoy！
<br>The first version, more simple interface and function Download http://pan.baidu.com/share/link?shareid=85286&uk=3860164064
<br>The second version, preliminary wrote the lyrics show synchronization function and peel function, (will *. SKN files on the application with directory can switch the skin) Download address: http://pan.baidu.com/share/link?shareid=85287&uk=3860164064
<br>The third version, use a clock control to realize the magnetic form, continue to study... Download address:http://pan.baidu.com/share/link?shareid=85288&uk=3860164064
<br>The fourth version, call bass. DLL, completely overturned previous algorithm, interface beautiful many, can adjust equalizer, bug rarely, the function to increase...
<br>Download address: http://pan.baidu.com/share/link?shareid=85288&uk=3860164064
<br>The fifth version, magnetic form control change, win7 can perfect embodiment. Download address: http://pan.baidu.com/share/link?shareid=85290&uk=3860164064
<br>The sixth edition, realize the basic function of all, there is no screenshots, Download address:http://pan.baidu.com/share/link?shareid=85291&uk=3860164064
<br>The seventh version, automatic recording playlists and loading, there are a lot of new features in the skin to write control, wrote two skin, no record position replace then that's ink, generating two procedures, with their preferences, download to use it Bronze:http://pan.baidu.com/share/link? ... uk=3860164064Winamp version:：http://pan.baidu.com/share/link?shareid=85292&uk=3860164064
<br>1.7：http://pan.baidu.com/share/link?shareid=85302&uk=3860164064
<br>1.7.1：http://pan.baidu.com/share/link?shareid=85304&uk=3860164064
<br>1.7.2：http://pan.baidu.com/share/link?shareid=85305&uk=3860164064
<br>Finally, there are several skin file, download after decompression, 2-6 version can use, in their executive directory can switch the skin... Download skin: http://pan.baidu.com/share/link?shareid=85298&uk=3860164064
<br>No......
<br>Small step listen updated to 1.8, (1.8-1.8.2 three desktop lyrics effect), win7 next display effect is good. Fixed bug, welcome to download the trial! (XP effect general), After I test, bug ratio of 1.7 little a lot of, can the normal use, lyrics part are trying to! The original Delphi wrote a magnetic form code, in XP is still under 't-hurt-you-but-can 't-help-you, win7 effect is very good! 2. Add similar qq near the side to be automatic hidden expansion function Kind of desktop lyrics style, with your be fond of . All versions are after download decompression to use! . 1.8.2 version desktop lyrics I added display pictures, that folder must have a "Bruce. PNG", you can freely use their like picture, but name must agree, otherwise you will prompt "out of memory..." . All versions are single file executable program, no call DLL, a lot of people foreigners development of thing, after the call, encumbrance! No...
<br>Download address:
<br>1.8： http://pan.baidu.com/share/link?shareid=85307&uk=3860164064 1.8.1： http://pan.baidu.com/share/link?shareid=85308&uk=3860164064 1.8.2： http://pan.baidu.com/share/link?shareid=85308&uk=3860164064
<br>/ / - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Had a holiday, nothing to do research and study, update some function - - - - - - - - - - - - - - - 2012/10/1 now To read the id3 tags, can define your own artist, album, s,,,, a key definition, characteristic summary. 2. Join recognition operating system version, and then to control the form of magnetic effect. Join desktop lyrics maintain the front end function, I don't know his thousands of listening algorithm, oneself take it for granted
<br>Download address:
<br>1.8.3： http://pan.baidu.com/share/link?shareid=85311&uk=3860164064
<br>//-----------------------------------------------------------------------------------------------------
<br>
<br>Just finished the test, update some bug, - - - - - - - - - - - - - - - for 2012/10/23
<br>1. Fixed label the revised don't immediately display bug
<br>2. Modify desktop lyrics rolling algorithm,
<br>3. Add a few small optimization
<br>Download: 1.8.4 ：http://pan.baidu.com/share/link?shareid=89446&uk=3860164064
<br>////////////////////////////////////////////////////////////////历史更新/////////////////////////////////////////
					</div>
				</div>
			</div>
		</div>
</div>

    <div class="clearfix maincontent">
	    <div class="container">
	   
</div>

	    </div>
	

<%@ include file="/page/common/container.jsp"%>

<script async="" src="/js/analytics.js"></script><script src="/js/jquery-1.11.0.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/main2.js"></script>

    




<script type="text/javascript">

$(document).ready(function() {

	var ajax_url='/ajax';
	var _aj = {user_id: '50777'};
	_aj['user_agent']='68A697E775AE';
	_aj['timestamp']='1400553338';
	_aj['user_keychain']='956FC269E368';

	
	});
	
	function toEditInfo(){
		$("#f1").submit();
	}
	
	function toView(id){
		$("#f2").attr("action","lyrics/toEdit.action?id="+id).submit();
	}
	
	function addSpan(obj){
		document.getElementById(obj).className = "span";
	}

	function rmSpan(obj){
		document.getElementById(obj).className = "";
	}

	function removes(lyricsid,userlyricsid){
		art.dialog.confirm('你确定要删除这首最爱歌词吗？', function () {
		    $("#f2").attr("action","lyrics/remove.action?lyricsid="+lyricsid+"&userlyricsid="+userlyricsid).submit();
		}, function () {
		    return ;
		});
	}

</script>

 

</body></html>    
