package com.zhump.lian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print("<!DOCTYPE html>");
		writer.println("<html lang=\"en\">");
		writer.println("<head>");
		writer.println("<meta charset=\"UTF-8\">");
		writer.println("<title>Title</title>");
		writer.println("<style type=\"text/css\">");
		writer.println("#content{");
		writer.println("margin:0 auto;/*元素居中处理*/}");
		writer.println("a{text-decoration:none;}");
		writer.println("table , td ,th{border:1px dashed red;border-collapse:collapse;/*细线表格，合并边框*/}");
		writer.println("</style>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<div>");
		writer.println("<table id=\"content\" width=\"800\" height=\"300\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		writer.println("<caption><h3>个人信息</h3></caption>");
		writer.println("<tr bgcolor=\"#ffffff\">");
		writer.println("<td>头像</td>");
		writer.println("<td><img src=\"\"/></td></tr>");
		writer.println("<tr bgcolor=\"#ffffff\">");
		writer.println("<td>用户名</td>");
		writer.println("<td>2</td></tr>");
		writer.println("<tr bgcolor=\"#ffffff\">>");
		writer.println("<td>昵称</td>");
		writer.println("<td>2</td>");
		writer.println("</tr>");
		writer.println("<tr bgcolor=\"#ffffff\">");
		writer.println("<td>性别</td>");
		writer.println("<td>2</td></tr>");
		writer.println("<tr bgcolor=\"#ffffff\">");
		writer.println("<td>省</td>");
		writer.println("<td>2</td></tr>");
		writer.println("<tr bgcolor=\"#ffffff\">");
		writer.println("<td>城市</td>");
		writer.println("<td>2</td>");
		writer.println("</tr>");
		writer.println("</table>");
		writer.println("</div>");
		writer.println("</body>");
		writer.println("</html>");
	}

}
