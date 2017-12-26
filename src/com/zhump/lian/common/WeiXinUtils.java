package com.zhump.lian.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.alibaba.fastjson.JSON;
import com.zhump.lian.pojo.AuthToken;
import com.zhump.lian.pojo.button.Button;
import com.zhump.lian.pojo.button.ClickButton;
import com.zhump.lian.pojo.button.Menu;
import com.zhump.lian.pojo.UserInfo;
import com.zhump.lian.pojo.Industry;
import com.zhump.lian.pojo.button.ViewButton;
import com.zhump.lian.pojo.template.Data;
import com.zhump.lian.pojo.template.First;
import com.zhump.lian.pojo.template.OrderMoneySum;
import com.zhump.lian.pojo.template.OrderProductName;
import com.zhump.lian.pojo.template.Remark;
import com.zhump.lian.pojo.template.TemplatePojo;

import net.sf.json.JSONObject;

/**
 * 
* 项目名称：zhump_weixin   
* 类名称：WeiXinUtils   
* 类描述：   
* 创建人：zmp
* 创建时间：2017年12月15日 上午10:32:24     
* 修改备注：   
* @version V1.0
 */
public class WeiXinUtils {

	
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    
    public static final String TEST_ID = "wxca97eff760bac2a1";
    
    public static final String  TEST_SECRET = "f677573ce076cb8364425c282e837efb";
    
    /**
     *获取access_token
     */
    public static final String MENU_CONSTANT = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    
    /**
     * 设置行业
     */
    public static final String TEMPLATE_CONSTANT = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
    
    /**
     * 获取行业
     */
    public static final String GET_INDEUSTRY_DATA = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
    
    /**
     * 添加模板
     */
    public static final String GET_TEMPLATE_ID = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
    
    /**
     * 发送模板
     */
    public static final String SET_TEMPLATE_DATA = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    
    /**
     * 获取模板列表
     */
    public static final String GET_TEMLATE_LIST = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
    
    
    /**
	 * 获取accessToken
	 */
	public static String getAccessToken(){
        String result =null;
        String url =" https://api.weixin.qq.com/cgi-bin/token";//请求接口地址
        Map<String, Object> params = new HashMap<>();//请求参数
            params.put("grant_type","client_credential");
            params.put("appid", TEST_ID);
            params.put("secret", TEST_SECRET);
            params.put("dtype","");//返回数据的格式,xml或json，默认json
        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            String accessToken = object.getString("access_token");
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	/**
	 * 
	* @author zmp
	* @Title: getAuthToken 
	* @Description: 网页授权token
	* @return AuthToken    返回类型 
	* @version V1.0
	 */
	public static AuthToken getAuthToken(String code) {
		String result =null;
        String url ="https://api.weixin.qq.com/sns/oauth2/access_token";//请求接口地址
        Map<String, Object> params = new HashMap<>();//请求参数
            params.put("appid",TEST_ID);//接收accessToken
            params.put("secret", TEST_SECRET);
            params.put("code", code);
            params.put("grant_type", "authorization_code");
            params.put("dtype","");//返回数据的格式,xml或json，默认json
        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            String token = object.getString("access_token");
            String openid = object.getString("openid");
            AuthToken authToken = new AuthToken();
            authToken.setAccessToken(token);
            authToken.setOpenId(openid);
            return authToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	/**
	 * 
	* @author zmp
	* @Title: getUserInfo 
	* @Description: 获取用户信息
	* @return UserInfo    返回类型 
	* @version V1.0
	 */
	public static UserInfo getUserInfo(String token, String openId) {
		String result = null;
		String url = "https://api.weixin.qq.com/sns/userinfo";
		Map<String, Object> params = new HashMap<>();// 请求参数
		params.put("access_token", token);// 接收accessToken
		params.put("openid", openId);
		params.put("lang", "zh_CN");
		params.put("dtype", "");// 返回数据的格式,xml或json，默认json
		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			System.out.println("object:"+object.toString());
			UserInfo userInfo = new UserInfo();
			userInfo.setOpenId(object.getString("openid"));
			userInfo.setNickName(object.getString("nickname"));
			userInfo.setSex(Integer.valueOf(object.getString("sex")));
			userInfo.setProvince(object.getString("province"));
			userInfo.setCountry(object.getString("country"));
			userInfo.setCity(object.getString("city"));
			userInfo.setHeadimgurl(object.getString("headimgurl"));
			return userInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @author zmp 
	 * @Title: getRequest2 
	 * @Description: 设置菜单接口 
	 * @param @param
	 * accessToken 设定文件
	 * @return void 返回类型
	 *  @version V1.0 @throws
	 */
	
	  public static JSONObject MenuCreate(String JsonMenu,String accessToken){
		  String url=MENU_CONSTANT.replace("ACCESS_TOKEN", accessToken);
		  try { 
			  JSONObject result = httpRequest(url,"POST",JsonMenu);
			  JSONObject object = JSONObject.fromObject(result);
			  return object; 
		  } catch (Exception e) { 
			  e.printStackTrace(); 
		  } 
		  return null; 
	  }
	  
	  public static JSONObject MenuCreates(String JsonMenu,String accessToken){
		  String result = null;
		  String url="https://api.weixin.qq.com/cgi-bin/menu/create";
		  
		  Map<String,String> parma = new HashMap<>();
		  parma.put("access_token", accessToken);
		  parma.put("dtype", "");
		  try { 
			  result = net(url, parma, "POST");
				JSONObject object = JSONObject.fromObject(result);
				System.out.println("object:"+object.toString());
			  return object; 
		  } catch (Exception e) { 
			  e.printStackTrace(); 
		  } 
		  return null; 
	  }
	  
	  
	  /**
	   * 
	  * @author zmp
	  * @Title: getIndustry 
	  * @Description: 组装行业
	  * @return String    返回类型 
	  * @version V1.0
	   */
	  public static String getIndustry() {
		  Industry industry = new Industry();
		  industry.setIndustry_id1("1");
		  industry.setIndustry_id2("4");
		  String string = JSONObject.fromObject(industry).toString();
		  return string;
	  }
	  
	  /**
	   * 
	  * @author zmp
	  * @Title: create 
	  * @Description: 设置行业
	  * @return JSONObject    返回类型 
	  * @version V1.0
	   */
	  public static JSONObject createIndustry(String industry,String accessToken) {
		  String url=TEMPLATE_CONSTANT.replace("ACCESS_TOKEN", accessToken);
		  try { 
			  JSONObject result = httpRequest(url,"POST",industry);
			  JSONObject object = JSONObject.fromObject(result);
			  return object; 
		  } catch (Exception e) { 
			  e.printStackTrace(); 
		  } 
		  return null; 
	  }
	  
	  /**
	   * 
	  * @author zmp
	  * @Title: getIndustryData 
	  * @Description: 获取行业基本信息
	  * @return JSONObject    返回类型 
	  * @version V1.0
	   */
	  public static JSONObject getIndustryData(String accessToken) {
		  String url=GET_INDEUSTRY_DATA.replace("ACCESS_TOKEN", accessToken);
		  try { 
			  JSONObject result = httpRequest(url,"GET",null);
			  JSONObject object = JSONObject.fromObject(result);
			  return object; 
		  } catch (Exception e) { 
			  e.printStackTrace(); 
		  } 
		  return null; 
	  }
	  
	  /**
	   * 
	  * @author zmp
	  * @Title: getTemplateId 
	  * @Description: 获取模板id 
	  * @return JSONObject    返回类型 
	  * @version V1.0
	   */
	  public static JSONObject getTemplateId(String templateId,String accessToken) {
		  String url=GET_TEMPLATE_ID.replace("ACCESS_TOKEN", accessToken);
		  try { 
			  JSONObject result = httpRequest(url,"POST",templateId);
			  JSONObject object = JSONObject.fromObject(result);
			  return object; 
		  } catch (Exception e) { 
			  e.printStackTrace(); 
		  } 
		  return null; 
	  }
	  
	  /**
	   * 
	  * @author zmp
	  * @Title: sendTemplate 
	  * @Description: 发送模板
	  * @return JSONObject    返回类型 
	  * @version V1.0
	   */
	  public static JSONObject sendTemplate(String templateData,String accessToken) {
		  String url=SET_TEMPLATE_DATA.replace("ACCESS_TOKEN", accessToken);
		  try { 
			  JSONObject result = httpRequest(url,"POST",templateData);
			  JSONObject object = JSONObject.fromObject(result);
			  return object; 
		  } catch (Exception e) { 
			  e.printStackTrace(); 
		  } 
		  return null; 
	  }
	  

	  
	  /**
	   * 获取模板列表
	   */
	  public static JSONObject getTemplateList(String accessToken) {
		  String url=GET_TEMLATE_LIST.replace("ACCESS_TOKEN", accessToken);
		  try { 
			  JSONObject result = httpRequest(url,"POST",null);
			  JSONObject object = JSONObject.fromObject(result);
			  return object; 
		  } catch (Exception e) { 
			  e.printStackTrace(); 
		  } 
		  return null; 
	  }

	/**
	 * 
	* @author zmp
	* @Title: net 
	* @Description: TODO(描述) 
	* @return String    返回类型 
	* @version V1.0
	 */
   @SuppressWarnings("unchecked")
   public static String net(String strUrl, @SuppressWarnings("rawtypes") Map params,String method) throws Exception {
       HttpURLConnection conn = null;
       BufferedReader reader = null;
       String rs = null;
       try {
           StringBuffer sb = new StringBuffer();
           if(method==null || method.equals("GET")){
               strUrl = strUrl+"?"+urlencode(params);
           }
           URL url = new URL(strUrl);
           conn = (HttpURLConnection) url.openConnection();
           if(method==null || method.equals("GET")){
               conn.setRequestMethod("GET");
           }else{
               conn.setRequestMethod("POST");
               conn.setDoOutput(true);
           }
           conn.setRequestProperty("User-agent", userAgent);
           conn.setRequestProperty("accept", "*/*");  
           conn.setRequestProperty("connection", "Keep-Alive");  
           conn.setUseCaches(false);
           conn.setConnectTimeout(DEF_CONN_TIMEOUT);
           conn.setReadTimeout(DEF_READ_TIMEOUT);
           conn.setInstanceFollowRedirects(false);
           conn.connect();
           if (params!= null && method.equals("POST")) {
               try {
                   DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                       out.writeBytes(urlencode(params));
               } catch (Exception e) {
                   // TODO: handle exception
               }
           }
           InputStream is = conn.getInputStream();
           reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
           String strRead = null;
           while ((strRead = reader.readLine()) != null) {
               sb.append(strRead);
           }
           rs = sb.toString();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (reader != null) {
               reader.close();
           }
           if (conn != null) {
               conn.disconnect();
           }
       }
       return rs;
   }

   //将map型转为请求参数型
   @SuppressWarnings("rawtypes")
public static String urlencode(Map<String,Object>data) {
       StringBuilder sb = new StringBuilder();
       for (Map.Entry i : data.entrySet()) {
           try {
               sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
       }
       return sb.toString();
   }
   
   /**
    * 组装菜单
    */
   
   public static String createMenu() {
	   Menu menu = new Menu();
	   //click菜单
	   ClickButton clickButton = new ClickButton();
	   clickButton.setType("click");
	   clickButton.setName("菜单一");
	   clickButton.setKey("1");
	   
	   ViewButton viewButton = new ViewButton();
	   viewButton.setName("菜单二");
	   viewButton.setType("view");
	   viewButton.setUrl("http://zhump.free.ngrok.cc");
	   
	   ClickButton clickButton31 = new ClickButton();
	   clickButton31.setType("click");
	   clickButton31.setName("三菜单一");
	   clickButton31.setKey("11");
	   
	   ViewButton viewButton32 = new ViewButton();
	   viewButton32.setName("三菜单二");
	   viewButton32.setType("view");
	   viewButton32.setUrl("http://zhump.free.ngrok.cc");
	   
	   ClickButton clickButton33 = new ClickButton();
	   clickButton33.setType("click");
	   clickButton33.setName("三菜单三");
	   clickButton33.setKey("33");
	   
	  Button button = new Button();
	   button.setName("菜单三");
	   button.setSub_button(new Button[] {clickButton31,viewButton32,clickButton33,});
	   
	   menu.setButton(new Button[] {clickButton,viewButton,button});
	   String string = JSONObject.fromObject(menu).toString();
	   return string;
   }
   
   
   
   /**
    * 组装模板
    */
   
   public static String createTemplatePojo(String templateId) {
	   TemplatePojo templatePojo = new TemplatePojo();
	   templatePojo.setTouser("op_5qwxdRNDeRr7VtcrjAE5xNlXs");
	   templatePojo.setTemplate_id(templateId);
	   Data data = new Data();
	   First first = new First();
	   first.setValue("恭喜购买成功");
	   OrderMoneySum keynote1 = new OrderMoneySum();
	   keynote1.setValue("39.8元");
	   
	   OrderProductName keynote2 = new OrderProductName();
	   keynote2.setValue("田田圈");
	   
	   Remark remark = new Remark();
	   remark.setValue("如有问题请咨询阿里云");
	   data.setFirst(first);
	   data.setOrderMoneySum(keynote1);
	   data.setOrderProductName(keynote2);
	   data.setRemark(remark);
	   templatePojo.setData(data);
	   String string = JSON.toJSONString(templatePojo).toString();
	   return string;
   }
   
   /**
    * 
   * @author zmp
   * @Title: httpRequest 
   * @Description: TODO(描述) 
   * @return JSONObject    返回类型 
   * @version V1.0
    */
   public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
	   JSONObject jsonObject = null;
       StringBuffer buffer = new StringBuffer(); 
       try {  
           // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
           TrustManager[] tm = { new MyX509TrustManager() };  
           SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
           sslContext.init(null, tm, new java.security.SecureRandom());  
           // 从上述SSLContext对象中得到SSLSocketFactory对象  
           SSLSocketFactory ssf = sslContext.getSocketFactory();  
           URL url = new URL(requestUrl);  
           HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
           httpUrlConn.setSSLSocketFactory(ssf);  
 
           httpUrlConn.setDoOutput(true);  
           httpUrlConn.setDoInput(true);  
           httpUrlConn.setUseCaches(false);  
           // 设置请求方式（GET/POST）  
           httpUrlConn.setRequestMethod(requestMethod);  
 
           if ("GET".equalsIgnoreCase(requestMethod))  
               httpUrlConn.connect();  
 
           // 当有数据需要提交时  
           if (null != outputStr) {  
               OutputStream outputStream = httpUrlConn.getOutputStream();  
               // 注意编码格式，防止中文乱码  
               outputStream.write(outputStr.getBytes("UTF-8"));  
               outputStream.close();  
           }  
 
           // 将返回的输入流转换成字符串  
           InputStream inputStream = httpUrlConn.getInputStream();  
           InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
           BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
 
           String str = null;  
           while ((str = bufferedReader.readLine()) != null) {  
               buffer.append(str);  
           }  
           bufferedReader.close();  
           inputStreamReader.close();  
           // 释放资源  
           inputStream.close();  
           inputStream = null;  
           httpUrlConn.disconnect();  
           jsonObject = JSONObject.fromObject(buffer.toString());  
       } catch (ConnectException ce) {  
    	   ce.printStackTrace();
       } catch (Exception e) {  
           e.printStackTrace();
       }  
       return jsonObject;  
   }  

}
