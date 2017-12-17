package com.zhump.lian;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.zhump.lian.common.MessageConstant;
import com.zhump.lian.common.MessageUtils;
import com.zhump.lian.pojo.Message;





/**
 * 
* 项目名称：zhump_weixin   
* 类名称：WeiXinServlet   
* 类描述：   
* 创建人：zmp
* 创建时间：2017年12月12日 下午2:06:01     
* 修改备注：   
* @version V1.0
 */
public class WeiXinServlet extends HttpServlet{

	/** 
	* @author zmp
	* @Fields serialVersionUID : TODO(描述) 
	* @version V1.0
	*/ 
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置字符集
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		connect(req,out);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//回送信息
		echorMsg(req,resp);
	}
	
	/**
	 * @throws IOException 
	 * 回送消息
	* @author zmp
	* @Title: echorMsg 
	* @Description: TODO(描述) 
	* @param @param req
	* @param @param resp    设定文件 
	* @return void    返回类型 
	* @version V1.0
	* @throws
	 */
	private void echorMsg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		Map<String,String> xmlMap = null;
		try {
			xmlMap = MessageUtils.xmlToMap(req.getInputStream());
			Message message = MessageUtils.MapToMessage(xmlMap);
			String fromUserName = message.getFromUserName();
			String toUserName = message.getToUserName();
			message.setFromUserName(toUserName);
			message.setToUserName(fromUserName);
			message.setCreateTime(System.currentTimeMillis());
			String content = message.getContent();
			//判断是否为空
			content = null == content?"":content.trim();
			//书写返回数据
			String reply = "";
			//获取消息类型
			String type = xmlMap.get("MsgType");
			if(type.equals(MessageConstant.TEXT)) {
				if(content.equals("?") || content.equals("？")) {
					reply = ceShiText();
				}else if(content.equals("1") || content.equals("z")) {
					reply = ceShiTextOne();
				}else if(content.equals("2") || content.equals("zmp")){
					reply = zmpText();
				}else if(content.equals("3") || content.equals("hong")){
					reply = hongText();
				}else {
					reply = hongTexts();
				}
			}else if(type.equals(MessageConstant.EVENT)) {
				//获取事件类型
				String eventType = xmlMap.get("Event").toLowerCase();
				//关注之后
				if (eventType.equals(MessageConstant.SUBSCRIBE)) {
					reply = ceShiText();
					message.setMsgType(MessageConstant.TEXT);
				}else if(eventType.equals(MessageConstant.UNSUBSCRIBE)){
					System.out.println("有人取消关注");
				}
			}
			message.setContent(reply);
			String toXml = MessageUtils.MessageToXml(message);
			//打印测试
			System.out.println(toXml);
			//将消息回送
			writer.print(toXml);
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String hongTexts() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("回复?回到功能介绍\n");
		return buffer.toString();
	}

	private String ceShiTextOne() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎你加入了可惜了\n");
		buffer.append("1:回忆杀\n");
		buffer.append("1)bang bang bang\n");
		buffer.append("2)奔放\n");
		buffer.append("3)喜欢你DJ");
		return buffer.toString();
	}

	private String hongText() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎你加入了可惜了\n");
		buffer.append("3:摇滚曲目\n");
		buffer.append("1)bang bang bang\n");
		buffer.append("2)奔放\n");
		buffer.append("3)喜欢你DJ\n");
		return buffer.toString();
	}

	private String zmpText() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎你加入了可惜了\n");
		buffer.append("2:粤语金典曲目\n");
		buffer.append("1)讲不出再见\n");
		buffer.append("2)喜欢你\n");
		buffer.append("3)灰色轨迹\n");
		return buffer.toString();
	}

	private String ceShiText() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎你加入了可惜了\n");
		buffer.append("1:回忆杀曲目\n");
		buffer.append("2:粤语金典曲目\n");
		buffer.append("3:摇滚曲目\n");
		buffer.append("回复?回到次功能介绍");
		return buffer.toString();
	}

	/**
	 * 接入
	* @author zmp
	* @Title: connect 
	* @Description: TODO(描述) 
	* @param @param req
	* @param @param out    设定文件 
	* @return void    返回类型 
	* @version V1.0
	* @throws
	 */
	private void connect(HttpServletRequest req, PrintWriter out) {
		//接受微信服务器那边的四个参数
		//微信返回的加密参数
		String signature = req.getParameter("signature");
		//时间戳
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		//按照字典排序
		List<String> list = new ArrayList<>();
		//token名称要和微信配置那边一致
		list.add("zhump");
		list.add(timestamp);
		list.add(nonce);
		//按照字典排序
		Collections.sort(list);
		//拼接字符串
		StringBuffer sb = new StringBuffer();
		for(String string : list) {
			sb.append(string);
		}
		//进行shl加密
		String str = DigestUtils.sha1Hex(sb.toString());
		System.out.println("微信服务返回的结果:"+signature);
		System.out.println("字典排序之后的结果:"+str);
		//对比
		boolean flag = str.equals(signature);
		if(flag) {
			System.out.println("接入成功");
			out.print(echostr);
			out.flush();
		}
	}
}
