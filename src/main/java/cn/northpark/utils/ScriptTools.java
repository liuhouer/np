package cn.northpark.utils;
/**
 * bruce edited
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScriptTools {

    public static void alert(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException {
        //弹窗
        PrintWriter out = response.getWriter();
        out.println("<script language=javascript>alert('" + msg + "');</script>");
        out.close();

    }


}
