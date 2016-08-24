package com.fanyongliang.book.beans;

public class UserAddressBean {
	/**
	 * 编号
	 */
	private int code;
	/**
	 * 用户信息主键
	 */
	private int userInfoCode;
	/**
	 * 地址
	 */
	private String userAddress;

	public UserAddressBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAddressBean(int code, int userInfoCode, String userAddress) {
		super();
		this.code = code;
		this.userInfoCode = userInfoCode;
		this.userAddress = userAddress;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getUserInfoCode() {
		return userInfoCode;
	}

	public void setUserInfoCode(int userInfoCode) {
		this.userInfoCode = userInfoCode;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

}
