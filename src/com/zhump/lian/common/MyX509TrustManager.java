package com.zhump.lian.common;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;


/**
 * 
* 项目名称：zhump_weixin   
* 类名称：MyX509TrustManager   
* 类描述：   
* 创建人：zmp
* 创建时间：2017年12月15日 下午4:02:15     
* 修改备注：   
* @version V1.0
 */
public class MyX509TrustManager implements X509TrustManager
{

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
    }  
  
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
    }  
  
    public X509Certificate[] getAcceptedIssuers() {  
        return null;  	
    }  

}
