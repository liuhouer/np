package cn.northpark.utils;
/**
 * bruce edited
 *
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScriptTools {
	
	public static void alert(HttpServletRequest request,HttpServletResponse response ,String msg)throws IOException{
		//弹窗
			PrintWriter out = response.getWriter();
		    out.flush();
		    out.println("<script language=javascript>alert('"+msg+"');</script>");
	
	}
	
	
	public void closeWindowWithAlert(HttpServletRequest request,HttpServletResponse response )throws IOException{
	//关闭,父窗口弹出对话框,子窗口直接关闭
		PrintWriter out = response.getWriter();
	    out.flush();
	    out.println("<script language=javascript>window.close();</script>");
	}
	
	public void closeWindowWithoutAlert(HttpServletRequest request,HttpServletResponse response )throws IOException{
		//关闭,父窗口和子窗口都不弹出对话框,直接关闭
			PrintWriter out = response.getWriter();
		    out.flush();
		    out.println("<script>");
			out.println("{top.opener =null;top.close();}");
			out.println("</script>");
	}
	
	
	
	public void openWinAndRefresh(HttpServletRequest request,HttpServletResponse response )throws IOException{
	//弹出窗口刷新当前页面width=200 height=200菜单。菜单栏,工具条,地址栏,状态栏全没有
		PrintWriter out = response.getWriter();
	    out.flush();
	    out.println("<script language=javascript>window.open('rows.aspx','newwindow','width=200,height=200')</script>");
	}
	
	public void openWinAndRefreshNow(HttpServletRequest request,HttpServletResponse response )throws IOException{
		//弹出窗口刷新当前页面
		PrintWriter out = response.getWriter();
	    out.flush();
		out.println("<script language=javascript>window.open('rows.aspx')</script>");
		out.println("<script>window.open('WebForm2.aspx','_blank');</script>");
	}
	
	public void openWinAndJump(HttpServletRequest request,HttpServletResponse response )throws IOException{
		//弹出提示窗口跳到webform2.aspx页(在一个IE窗口中)
		PrintWriter out = response.getWriter();
	    out.flush();
	    out.println(" <script language=javascript>alert('注册成功');window.window.location.href='WebForm2.aspx';</script> ");
	}
	
	
	public void closeMinWinAndRefreshMaxWin(HttpServletRequest request,HttpServletResponse response )throws IOException{
		//关闭当前子窗口,刷新父窗口
		PrintWriter out = response.getWriter();
	    out.flush();
	    out.println("<script>window.opener.location.href=window.opener.location.href;window.close();</script>");
		out.println("<script>window.opener.location.replace(window.opener.document.referrer);window.close();</script>");
	}


	public void minWinRefreshMaxWin(HttpServletRequest request,HttpServletResponse response )throws IOException{
	//子窗口刷新父窗口
		PrintWriter out = response.getWriter();
	    out.flush();
	    out.println("<script>window.opener.location.href=window.opener.location.href;</script>");
	    out.println("<script>window.opener.location.href='WebForm1.aspx';</script>");	
	 }
	
	public void alertToNewWin(HttpServletRequest request,HttpServletResponse response )throws IOException{
		//弹出提示窗口.确定后弹出子窗口(WebForm2.aspx)
			PrintWriter out = response.getWriter();
		    out.flush();
		    out.println("<script language='javascript'>alert('发表成功！');window.open('WebForm2.aspx')</script>");
	}

	public void alertToRefreshMaxWin(HttpServletRequest request,HttpServletResponse response ) throws IOException{
		//弹出提示窗口,确定后,刷新父窗口
		PrintWriter out = response.getWriter();
	    out.flush();
	    out.println("<script>alert('发表成功！');window.opener.location.href=window.opener.location.href;</script>");
	}
	/*
	 * 
	//弹出相同的一页
	<INPUT type="button" value="Button" onclick="javascript:window.open(window.location.href)">

	//
	Response.Write("parent.mainFrameBottom.location.href='yourwebform.aspx?temp=" +str+"';");


	<SCRIPT LANGUAGE="javascript">
	<!--
	window.open ('page.html', 'newwindow', 'height=100, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') //这句要写成一行
	-->
	</SCRIPT>  
	　　
	　　参数解释：
	　　
	　　<SCRIPT LANGUAGE="javascript"> js脚本开始；
	　　window.open 弹出新窗口的命令；
	　　'page.html' 弹出窗口的文件名；
	　　'newwindow' 弹出窗口的名字（不是文件名），非必须，可用空''代替；
	　　height=100 窗口高度；
	　　width=400 窗口宽度；
	　　top=0 窗口距离屏幕上方的象素值；
	　　left=0 窗口距离屏幕左侧的象素值；
	　　toolbar=no 是否显示工具栏，yes为显示；
	　　menubar，scrollbars 表示菜单栏和滚动栏。
	　　resizable=no 是否允许改变窗口大小，yes为允许；
	　　location=no 是否显示地址栏，yes为允许；
	　　status=no 是否显示状态栏内的信息（通常是文件已经打开），yes为允许；
	　　</SCRIPT> js脚本结束

	'newwin':隐藏菜单栏地址栏工具条
	width=50:宽度
	height=50:高度
	scrollbars=yes/n滚动条
	top=50:窗口距离屏幕上方
	left=50:窗口距离屏幕左侧
	例:window.open('detail.aspx?ID="+e.Item.Cells[1].Text+"','newwin','width=750,height=600,scrollbars=yes,top=50,left=50');");
	out.println("<Script>window.open('WebForm2.aspx','','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750,height=470,left=80,top=40');</script>");

	例:
	out.println("<script>alert('发表成功！');window.opener.location.href=window.opener.location.href;</script>");
	out.println("<script>");
	out.println("{top.opener =null;top.close();}");
	out.println("</script>");

	例: linkcolumn1.DataNavigateUrlFormatString="javascript:varwin=window.open('edit_usr.aspx?actid={0}','newwin','width=750,height=600,scrollbars=yes,top=50,left=50');window.close()";
*/
}
