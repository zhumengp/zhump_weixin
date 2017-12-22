package com.zhump.lian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhump.lian.common.WeiXinUtils;
import com.zhump.lian.pojo.AuthToken;
import com.zhump.lian.pojo.UserInfo;

/**
 * Servlet implementation class CallBackServlet
 */
@WebServlet("/callback")
public class CallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		System.out.println("code:"+code);
		AuthToken authToken = WeiXinUtils.getAuthToken(code);
		System.out.println("authToken:"+authToken.toString());
		//获取用户信息
		UserInfo userInfo = WeiXinUtils.getUserInfo(authToken.getAccessToken(),authToken.getOpenId());
		System.out.println("用户基本信息:"+userInfo.toString());
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
		writer.println("table , td ,th{font-size:25px;border:1px dashed red;border-collapse:collapse;/*细线表格，合并边框*/}");
		writer.println("</style>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<div>");
		writer.println("<table id=\"content\" width=\"800\" height=\"300\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		writer.println("<caption><h3>个人信息</h3></caption>");
		writer.println("<tr bgcolor=\"#ffffff\">");
		writer.println("<td>昵称</td>");
		writer.println("<td>"+userInfo.getNickName()+"</td></tr>");
		writer.println("<tr bgcolor=\"#ffffff\">>");
		writer.println("<td>国家</td>");
		writer.println("<td>"+userInfo.getCountry()+"</td>");
		writer.println("</tr>");
		writer.println("<tr bgcolor=\"#ffffff\">");
		writer.println("<td>性别</td>");
		writer.println("<td>"+userInfo.getSex()+"</td></tr>");
		writer.println("<tr bgcolor=\"#ffffff\">");
		writer.println("<td>省</td>");
		writer.println("<td>"+userInfo.getProvince()+"</td></tr>");
		writer.println("<tr bgcolor=\"#ffffff\">");
		writer.println("<td>城市</td>");
		writer.println("<td>"+userInfo.getCity()+"</td>");
		writer.println("</tr>");
		writer.println("</table>");
		writer.println("</div>");
		writer.println("</body>");
		writer.println("</html>");
	}

}
