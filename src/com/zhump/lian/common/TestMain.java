package com.zhump.lian.common;

import java.io.IOException;


import net.sf.json.JSONObject;

public class TestMain {
	
	public static void main(String[] args) throws IOException {

//		String accessToken = WeiXinUtils.getAccessToken();
//		System.out.println("token值:"+accessToken);
		
		//设置行业
//		String industry = WeiXinUtils.getIndustry();
//		System.out.println("设置行业:"+industry);
		
		JSONObject industryData = WeiXinUtils.getIndustryData("5_JVs7vIuduygV-sTma8flL8JYxZ4KCJdJK-kfZhYBoWE-7ty6mwLt75dUx-affAw-wty-Q6vc90u3HHuCBtJjfsKbEdYPa8RXQFxtsSxtJV25ANJy5QovHD9bDuXPq5reyfcsYWFs8d4w31_gTTScAFAJJI");
		System.out.println("获取行业信息:"+industryData);
		
		String a = "{\"template_id_short\":\"TM00015\"}";
		
		//获取模板id
//		JSONObject jSONObject = WeiXinUtils.getTemplateId(a, "5_JVs7vIuduygV-sTma8flL8JYxZ4KCJdJK-kfZhYBoWE-7ty6mwLt75dUx-affAw-wty-Q6vc90u3HHuCBtJjfsKbEdYPa8RXQFxtsSxtJV25ANJy5QovHD9bDuXPq5reyfcsYWFs8d4w31_gTTScAFAJJI");
//		String templateId = jSONObject.getString("template_id");
//		System.out.println("模板id:"+templateId);
		//获取模板列表
		JSONObject templateList = WeiXinUtils.getTemplateList("5_JVs7vIuduygV-sTma8flL8JYxZ4KCJdJK-kfZhYBoWE-7ty6mwLt75dUx-affAw-wty-Q6vc90u3HHuCBtJjfsKbEdYPa8RXQFxtsSxtJV25ANJy5QovHD9bDuXPq5reyfcsYWFs8d4w31_gTTScAFAJJI");
		System.out.println("模板列表:"+templateList);
		
		String createTemplatePojo = WeiXinUtils.createTemplatePojo("51CO-L36nvxUNyBAQl9INDOVRqd9SGOkYcZL6rjhxSs");
		System.out.println("发送模板信息："+createTemplatePojo);
		
		
		//发送模板
		JSONObject sendTemplate = WeiXinUtils.sendTemplate(createTemplatePojo, "5_JVs7vIuduygV-sTma8flL8JYxZ4KCJdJK-kfZhYBoWE-7ty6mwLt75dUx-affAw-wty-Q6vc90u3HHuCBtJjfsKbEdYPa8RXQFxtsSxtJV25ANJy5QovHD9bDuXPq5reyfcsYWFs8d4w31_gTTScAFAJJI");
		System.out.println("发送状态:"+sendTemplate);

	}
}
