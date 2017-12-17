package com.zhump.lian.pojo.template;

import net.sf.json.JSONObject;

public class Data {
	
	private First first;
	
	private OrderMoneySum orderMoneySum;
	
	private OrderProductName orderProductName;
	
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

	

	public Remark getRemark() {
		return remark;
	}

	public void setRemark(Remark remark) {
		this.remark = remark;
	}

	public void setFirst(First first) {
		this.first = first;
	}


	public static void main(String[] args) {
		Data data = new Data();
		Remark Remark = new Remark();
		Remark.setValue("niaho");
		data.setRemark(Remark);
		String string = JSONObject.fromObject(data).toString();
		System.out.println("string:"+string);
	}
}
