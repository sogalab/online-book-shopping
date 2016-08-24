package com.fanyongliang.book.beans;

import java.util.Date;

public class BookInfoBean {
	/**
	 * 编号
	 */
	private Integer code;
	/**
	 * 书名
	 */
	private String bookName;
	/**
	 * 单价
	 */
	private double bookPrice;
	/**
	 * 折扣
	 */
	private double bookDiscounts;
	/**
	 * 是否新书
	 */
	private Integer isNew;
	/**
	 * 是否畅销
	 */
	private Integer isHot;
	/**
	 * 评分是否最好
	 */
	private Integer isHigh;
	/**
	 * 是否折扣
	 */
	private Integer isDiscounts;
	/**
	 * 录入时间
	 */
	private Date inputTime;
	/**
	 * 作者
	 */
	private String bookAuthor;
	/**
	 * 出版社
	 */
	private String bookPress;
	/**
	 * 出版时间
	 */
	private String bookPublictionTime;
	/**
	 * 分类
	 */
	private String bookType;
	/**
	 * 描述
	 */
	private String bookDescribe;
	/**
	 * 封面
	 */
	private String bookPicture;
	/**
	 * 是否上架
	 */
	private Integer isInstore;
	/**
	 * 批次号
	 */
	private Integer bookBatch;
	/**
	 * ISBN号
	 */
	private String bookISBN;
	/**
	 * 库存
	 */
	private int bookCount;
	

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public BookInfoBean(Integer code, String bookName, double bookPrice,
			double bookDiscounts, Integer isNew, Integer isHot, Integer isHigh,
			Integer isDiscounts, Date inputTime, String bookAuthor,
			String bookPress, String bookPublictionTime, String bookType,
			String bookDescribe, String bookPicture, Integer isInstore,
			Integer bookBatch, String bookISBN) {
		super();
		this.code = code;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookDiscounts = bookDiscounts;
		this.isNew = isNew;
		this.isHot = isHot;
		this.isHigh = isHigh;
		this.isDiscounts = isDiscounts;
		this.inputTime = inputTime;
		this.bookAuthor = bookAuthor;
		this.bookPress = bookPress;
		this.bookPublictionTime = bookPublictionTime;
		this.bookType = bookType;
		this.bookDescribe = bookDescribe;
		this.bookPicture = bookPicture;
		this.isInstore = isInstore;
		this.bookBatch = bookBatch;
		this.bookISBN = bookISBN;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public BookInfoBean(Integer code, String bookName, double bookPrice,
			double bookDiscounts, Integer isNew, Integer isHot, Integer isHigh,
			Integer isDiscounts, Date inputTime, String bookAuthor,
			String bookPress, String bookPublictionTime, String bookType,
			String bookDescribe, String bookPicture, Integer isInstore,
			Integer bookBatch) {
		super();
		this.code = code;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookDiscounts = bookDiscounts;
		this.isNew = isNew;
		this.isHot = isHot;
		this.isHigh = isHigh;
		this.isDiscounts = isDiscounts;
		this.inputTime = inputTime;
		this.bookAuthor = bookAuthor;
		this.bookPress = bookPress;
		this.bookPublictionTime = bookPublictionTime;
		this.bookType = bookType;
		this.bookDescribe = bookDescribe;
		this.bookPicture = bookPicture;
		this.isInstore = isInstore;
		this.bookBatch = bookBatch;
	}

	public BookInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public double getBookDiscounts() {
		return bookDiscounts;
	}

	public void setBookDiscounts(double bookDiscounts) {
		this.bookDiscounts = bookDiscounts;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getIsHigh() {
		return isHigh;
	}

	public void setIsHigh(Integer isHigh) {
		this.isHigh = isHigh;
	}

	public Integer getIsDiscounts() {
		return isDiscounts;
	}

	public void setIsDiscounts(Integer isDiscounts) {
		this.isDiscounts = isDiscounts;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPress() {
		return bookPress;
	}

	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}

	public String getBookPublictionTime() {
		return bookPublictionTime;
	}

	public void setBookPublictionTime(String bookPublictionTime) {
		this.bookPublictionTime = bookPublictionTime;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getBookDescribe() {
		return bookDescribe;
	}

	public void setBookDescribe(String bookDescribe) {
		this.bookDescribe = bookDescribe;
	}

	public String getBookPicture() {
		return bookPicture;
	}

	public void setBookPicture(String bookPicture) {
		this.bookPicture = bookPicture;
	}

	public Integer getIsInstore() {
		return isInstore;
	}

	public void setIsInstore(Integer isInstore) {
		this.isInstore = isInstore;
	}

	public Integer getBookBatch() {
		return bookBatch;
	}

	public void setBookBatch(Integer bookBatch) {
		this.bookBatch = bookBatch;
	}
}
