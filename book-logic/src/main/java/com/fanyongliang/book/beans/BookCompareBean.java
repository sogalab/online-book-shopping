package com.fanyongliang.book.beans;

public class BookCompareBean {
	/**
	 * 编号
	 */
	private int code;
	/**
	 * 用户编号
	 */
	private int userCode;
	/**
	 * 图书编号
	 */
	private int bookCode;
	/**
	 * 图书名
	 */
	private String bookName;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public int getBookCode() {
		return bookCode;
	}

	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}

	public BookCompareBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookCompareBean(int code, int userCode, int bookCode, String bookName) {
		super();
		this.code = code;
		this.userCode = userCode;
		this.bookCode = bookCode;
		this.bookName = bookName;
	}

}
