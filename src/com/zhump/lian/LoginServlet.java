package com.zhump.lian;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.net.URLCodec;

import com.zhump.lian.common.MessageUtils;

/**
 * 
* 项目名称：zhump_weixin   
* 类名称：LoginServlet   
* 类描述：   授权登录
* 创建人：zmp
* 创建时间：2017年12月14日 下午1:49:43     
* 修改备注：   
* @version V1.0
 */
@WebServlet("/wxLogin")
public class LoginServlet extends HttpServlet{
	
	/** 
	* @author zmp
	* @Fields serialVersionUID : TODO(描述) 
	* @version V1.0
	*/ 
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String callbackUrl = "http://zhump.free.ngrok.cc/zhump_weixin/callback";
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize"
				+ "?appid="+MessageUtils.TEST_ID
				+ "&redirect_uri="+URLEncoder.encode(callbackUrl,"utf-8")
				+ "&response_type=code"
				+ "&scope=snsapi_userinfo"
				+ "&state=STATE#wechat_redirect";
		resp.sendRedirect(url);
	}

}
