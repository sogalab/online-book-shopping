package com.fanyongliang.book.beans;

public class ShoppingCarBean {
	/**
	 * 编号
	 */
	private int code;
	/**
	 * 图书信息主键
	 */
	private int bookCode;
	/**
	 * 用户信息主键
	 */
	private int userCode;
	/**
	 * 图书数量
	 */
	private int bookQuantity;
	/**
	 * 图书信息
	 */
	private BookInfoBean bookInfoBean;
	public BookInfoBean getBookInfoBean() {
		return bookInfoBean;
	}

	public void setBookInfoBean(BookInfoBean bookInfoBean) {
		this.bookInfoBean = bookInfoBean;
	}

	public ShoppingCarBean(int code, int bookCode, int userCode,
			int bookQuantity) {
		super();
		this.code = code;
		this.bookCode = bookCode;
		this.userCode = userCode;
		this.bookQuantity = bookQuantity;
	}

	public ShoppingCarBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getBookCode() {
		return bookCode;
	}

	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public int getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}

}
