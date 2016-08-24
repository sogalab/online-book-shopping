package com.fanyongliang.book.beans;

import java.io.Serializable;

public class UserInfoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 编号
	 */
	private int code;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 身份证号
	 */
	private String idCard;
	/**
	 * 密保邮箱
	 */
	private String userEmail;

	/**
	 * 密码
	 */
	private String userPasswd;
	/**
	 * 重复密码
	 */
	private String userPasswdRP;

	public String getUserPasswdRP() {
		return userPasswdRP;
	}

	public void setUserPasswdRP(String userPasswdRP) {
		this.userPasswdRP = userPasswdRP;
	}

	/**
	 * key
	 */
	private String passwdKey;
	/**
	 * 帐号类型
	 */
	private int userType;

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public String getPasswdKey() {
		return passwdKey;
	}

	public void setPasswdKey(String passwdKey) {
		this.passwdKey = passwdKey;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public UserInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInfoBean(int code, String userName, String realName,
			String idCard, String userEmail) {
		super();
		this.code = code;
		this.userName = userName;
		this.realName = realName;
		this.idCard = idCard;
		this.userEmail = userEmail;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
