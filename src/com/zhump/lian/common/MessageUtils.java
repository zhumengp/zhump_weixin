package com.zhump.lian.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.zhump.lian.pojo.AuthToken;
import com.zhump.lian.pojo.Message;
import com.zhump.lian.pojo.UserInfo;

import net.sf.json.JSONObject;

/**
 * 
* 项目名称：zhump_weixin   
* 类名称：MessageUtils   
* 类描述：   
* 创建人：zmp
* 创建时间：2017年12月12日 下午4:22:47     
* 修改备注：   
* @version V1.0
 */
public class MessageUtils {
	
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    
    public static final String APP_ID = "wxca6e1f8e22654b3e";
    
    public static final String TEST_ID = "wxca97eff760bac2a1";
    
    public static final String  SECRET = "a7a43005a6f90e2cf220ab581730cd0b";
    
    public static final String  TEST_SECRET = "f677573ce076cb8364425c282e837efb";
    
	
	/**
	 * 解析xml 转换成Map
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	public static Map<String,String> xmlToMap(InputStream inputStream) throws  Exception{
		
		SAXReader saxReader = new SAXReader();
		//读取xml文本信息
		Document document = saxReader.read(inputStream);
		//读取到xml根目录
		Element element = document.getRootElement();
		//读取到其他元素
		@SuppressWarnings("unchecked")
		List<Element> elements = element.elements();
		Map<String,String> map = new HashMap<String,String>();
		for(Element e : elements) {
			map.put(e.getName(), e.getText());
		}
		inputStream.close();
		return map;
		
	}
	
	/**
	 * 
	* @author zmp
	* @Title: MapToMessage 
	* @Description: Map 转换为 Message对象
	* @param @param map
	* @param @return    设定文件 
	* @return Message    返回类型 
	* @version V1.0
	* @throws
	 */
	public static Message MapToMessage(Map<String,String> map) {
		Message message = new Message();
		message.setToUserName(map.get("ToUserName"));
		message.setFromUserName(map.get("FromUserName"));
		message.setCreateTime(Long.valueOf(map.get("CreateTime")));
		message.setMsgType(map.get("MsgType"));
		message.setContent(map.get("Content"));
		message.setMsgId(map.get("MsgId"));
		return message;
		
	}
	
	/**
	 * 
	* @author zmp
	* @Title: MessageToXml 
	* @Description: 将对象转换成XML
	* @param @param message
	* @param @return    设定文件 
	* @return String    返回类型 
	* @version V1.0
	* @throws
	 */
	public static String MessageToXml(Message message) {
		XStream xStream = new XStream();
		xStream.alias("xml", message.getClass());
		return xStream.toXML(message);
	}
	
	
	
    
    
	
}
