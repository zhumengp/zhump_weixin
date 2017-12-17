package com.zhump.lian.pojo;

/**
 * 
* 项目名称：zhump_weixin   
* 类名称：Message   
* 类描述：   
* 创建人：zmp
* 创建时间：2017年12月13日 上午11:50:45     
* 修改备注：   
* @version V1.0
 */
public class Message {
	
	/**
	 * 开发者微信号
	 */
	private String ToUserName;
	
	/**
	 * 发送方微信号
	 */
	private String FromUserName;
	
	/**
	 * 创建时间
	 */
	private long CreateTime;
	
	/**
	 * 消息类型
	 */
	private String MsgType;
	
	/**
	 * 消息内容
	 */
	private String Content;
	
	/**
	 * 消息id
	 */
	private String MsgId;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}


	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	

}
