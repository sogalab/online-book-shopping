package com.fanyongliang.book.beans;

public class NavListBean {
	/**
	 * 编号
	 */
	private int code;
	/**
	 * 父导航名字
	 */
	private String navPareName;
	/**
	 * 子导航编号
	 */
	private int navChildCode;
	/**
	 * 是否显示
	 */
	private int isShow;

	public NavListBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NavListBean(int code, String navPareName, int navChildCode,
			int isShow) {
		super();
		this.code = code;
		this.navPareName = navPareName;
		this.navChildCode = navChildCode;
		this.isShow = isShow;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNavPareName() {
		return navPareName;
	}

	public void setNavPareName(String navPareName) {
		this.navPareName = navPareName;
	}

	public int getNavChildCode() {
		return navChildCode;
	}

	public void setNavChildCode(int navChildCode) {
		this.navChildCode = navChildCode;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

}
