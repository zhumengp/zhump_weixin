package com.zhump.lian.pojo.template;


import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
	
	private First first;
	
	private OrderMoneySum orderMoneySum;
	
	private OrderProductName orderProductName;
	
	@JSONField(name="Remark")
	//@JsonProperty(value="Remark") 
	private Remark remark;

	public First getFirst() {
		return first;
	}

	public OrderMoneySum getOrderMoneySum() {
		return orderMoneySum;
	}

	public void setOrderMoneySum(OrderMoneySum orderMoneySum) {
		this.orderMoneySum = orderMoneySum;
	}

	public OrderProductName getOrderProductName() {
		return orderProductName;
	}

	public void setOrderProductName(OrderProductName orderProductName) {
		this.orderProductName = orderProductName;
	}

	@JsonIgnore
	public Remark getRemark() {
		return remark;
	}
	@JsonIgnore
	public void setRemark(Remark remark) {
		this.remark = remark;
	}
	@JsonIgnore
	public void setFirst(First first) {
		this.first = first;
	}


	public static void main(String[] args) {
		Data data = new Data();
		Remark Remark = new Remark();
		Remark.setValue("niaho");
		data.setRemark(Remark);
		String string = JSON.toJSONString(data).toString();
		System.out.println("string:"+string);
	}
}
