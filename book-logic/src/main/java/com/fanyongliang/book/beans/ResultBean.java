package com.fanyongliang.book.beans;

public class ResultBean<T> {
	/**
	 * 是否
	 */
	private boolean isTrue;
	/**
	 * 返回信息
	 */
	private String message;
	/**
	 * 泛型
	 */
	private T t;

	public ResultBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultBean(boolean isTrue, String message, T t) {
		super();
		this.isTrue = isTrue;
		this.message = message;
		this.t = t;
	}

	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

}
