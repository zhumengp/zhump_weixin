package com.zhump.lian.pojo.template;



import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Data {
	
	private First first;
	
	private OrderMoneySum orderMoneySum;
	
	private OrderProductName orderProductName;
	
	/**
	 * 保证首字母大写，因为微信模板 互联网和点击技术的模板 描述Remark 要求 R 大写
	 */
	@JSONField(name="Remark")
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
}
