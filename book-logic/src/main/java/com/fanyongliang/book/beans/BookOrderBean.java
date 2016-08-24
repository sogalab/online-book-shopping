package com.fanyongliang.book.beans;

import java.util.Date;

public class BookOrderBean {
	/**
	 * 订单编号
	 */
	private int orderCode;
	/**
	 * 下单时间
	 */
	private Date orderTime;
	/**
	 * 下单时间
	 */
	private String orderTimeString;
	/**
	 * 订单状态
	 */
	private int orderStatus;
	/**
	 * 总价
	 */
	private double total;
	/**
	 * 收货地址
	 */
	private String userAddress;
	/**
	 * 用户编号
	 */
	private int userCode;

	public BookOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookOrderBean(Date orderTime, int orderStatus,
			double total, String userAddress, int userCode) {
		super();
		this.orderTime = orderTime;
		this.orderStatus = orderStatus;
		this.total = total;
		this.userAddress = userAddress;
		this.userCode = userCode;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderTimeString() {
		return orderTimeString;
	}

	public void setOrderTimeString(String orderTimeString) {
		this.orderTimeString = orderTimeString;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

}
