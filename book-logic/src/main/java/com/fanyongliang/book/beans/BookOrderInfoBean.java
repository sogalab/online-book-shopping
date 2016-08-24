package com.fanyongliang.book.beans;

import java.util.Date;

public class BookOrderInfoBean {
	/**
	 * 编号
	 */
	private int code;
	/**
	 * 订单号
	 */
	private int orderCode;
	/**
	 * 图书编号
	 */
	private int bookCode;
	/**
	 * 数量
	 */
	private int quantity;
	/**
	 * 单价
	 */
	private double payPrice;
	/**
	 * 图书信息
	 */
	private BookInfoBean bookInfoBean;
	/**
	 * 下单时间
	 */
	private String orderTimeString;
	/**
	 * 订单状态
	 */
	private int orderStatus;
	
	

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

	public BookInfoBean getBookInfoBean() {
		return bookInfoBean;
	}

	public void setBookInfoBean(BookInfoBean bookInfoBean) {
		this.bookInfoBean = bookInfoBean;
	}

	public BookOrderInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookOrderInfoBean(int orderCode, int bookCode,
			int quantity, double payPrice) {
		super();
		this.orderCode = orderCode;
		this.bookCode = bookCode;
		this.quantity = quantity;
		this.payPrice = payPrice;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public int getBookCode() {
		return bookCode;
	}

	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(double payPrice) {
		this.payPrice = payPrice;
	}

}
