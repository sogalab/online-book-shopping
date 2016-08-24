package com.fanyongliang.book.beans;

public class AdvPhotoBean {
	/**
	 * 编号
	 */
	private int code;
	/**
	 * 描述
	 */
	private String photoDescribe;
	/**
	 * 图片名
	 */
	private String fileName;

	public AdvPhotoBean(int code, String photoDescribe, String fileName) {
		super();
		this.code = code;
		this.photoDescribe = photoDescribe;
		this.fileName = fileName;
	}

	public AdvPhotoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getPhotoDescribe() {
		return photoDescribe;
	}

	public void setPhotoDescribe(String photoDescribe) {
		this.photoDescribe = photoDescribe;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
