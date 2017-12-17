package com.zhump.lian.pojo;

public class AuthToken {

	private String accessToken;
	
	private String openId;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	public String toString() {
		return "AuthToken [accessToken=" + accessToken + ", openId=" + openId + "]";
	}
	
	
}