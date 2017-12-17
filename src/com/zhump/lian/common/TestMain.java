package com.zhump.lian.common;

import java.io.IOException;


import net.sf.json.JSONObject;

public class TestMain {
	
	public static void main(String[] args) throws IOException {

		String accessToken = WeiXinUtils.getAccessToken();
		System.out.println("token值:"+accessToken);
		
		String createMenu = WeiXinUtils.createMenu();
		
		JSONObject menuCreate = WeiXinUtils.MenuCreates(createMenu, accessToken);
		System.out.println("menuCreate:"+menuCreate);

	}
	
	public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
       return  name;
     
    } 

}
