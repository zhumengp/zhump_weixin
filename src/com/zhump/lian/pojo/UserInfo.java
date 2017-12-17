package com.zhump.lian.pojo;

/**
 * 
* 项目名称：zhump_weixin   
* 类名称：UserInfo   
* 类描述：  用户信息 
* 创建人：zmp
* 创建时间：2017年12月14日 下午2:39:47     
* 修改备注：   
* @version V1.0
 */
public class UserInfo {

	private String openId;
	
	private String nickName;
	
	private Integer sex;
	
	private String province;
	
	private String city;
	
	private String country;
	
	private String headimgurl;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	@Override
	public String toString() {
		return "UserInfo [openId=" + openId + ", nickName=" + nickName + ", sex=" + sex + ", province=" + province
				+ ", city=" + city + ", country=" + country + ", headimgurl=" + headimgurl + "]";
	}
	
	
}
