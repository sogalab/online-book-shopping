package com.fanyongliang.book.beans;

/**
 * 用户反馈实体表
 */
public class UserFeedbackBean {
	private int code;
	private String userName;
	// 反馈
	private String feedback;

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

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public UserFeedbackBean(String userName, String feedback) {
		super();
		this.userName = userName;
		this.feedback = feedback;
	}

	public UserFeedbackBean() {
		super();
	}

}
