package com.fanyongliang.book.bll;

import java.util.List;

import com.fanyongliang.book.beans.BookInfoBean;
import com.fanyongliang.book.dao.BookInfoDAO;

/**
 * 
 * @author 
 *
 */
public class BookInfoBll {
	private BookInfoDAO bookInfoDAO = new BookInfoDAO(); 
	/**
	 * 减少书本库存
	 * @param bookCode
	 * @param num
	 */
	public void updateBookCountReduce(Integer bookCode , Integer num){
		bookInfoDAO.updateBookCountReduce(bookCode, num);
	}
	
	/**
	 * 新增未上架图书
	 * @param bookInfoBean
	 */
	public void addOutStoreBook(BookInfoBean bookInfoBean) {
		bookInfoDAO.addOutStoreBook(bookInfoBean);
		
	}
	/**
	 * 查询所有未上架的图书
	 */
	public List<BookInfoBean> selectOutStoreBook() {
		return bookInfoDAO.selectOutStoreBook();
	}
	/**
	 *  删除未上架的图书
	 */
	public void deleteOutStoreBook(Integer code) {
		bookInfoDAO.deleteOutStoreBook(code);
	}
	/**
	 * 修改未上架图书
	 * @param bookInfoBean
	 */
	public void updateOutStoreBook(BookInfoBean bookInfoBean) {
		bookInfoDAO.updateOutStoreBook(bookInfoBean);
	}
	/**
	 *  未上架 -->上架
	 */
	public void inOutStoreBook(Integer code) {
		bookInfoDAO.inOutStoreBook(code);
	}
	/**
	 * 查询所有已上架的图书
	 */
	public List<BookInfoBean> selectInStoreBook() {
		return bookInfoDAO.selectInStoreBook();
	}
	/**
	 *  添加折扣
	 */
	public void instorebookdisc(BookInfoBean bookInfoBean) {
		bookInfoDAO.instorebookdisc(bookInfoBean);
	}
	/**
	 *  取消折扣
	 */
	public void deleteinstorebookdisc(Integer code) {
		bookInfoDAO.deleteinstorebookdisc(code);
	}
	/**
	 *  新书推荐
	 */
	public void instorebooknew(Integer code) {
		bookInfoDAO.instorebooknew(code);
	}
	/**
	 *  取消新书推荐
	 */
	public void deleteinstorebooknew(Integer code) {
		bookInfoDAO.deleteinstorebooknew(code);
	}
	/**
	 *  热门推荐
	 */
	public void instorebookhot(Integer code) {
		bookInfoDAO.instorebookhot(code);
	}
	/**
	 *  取消热门推荐
	 */
	public void deleteinstorebookhot(Integer code) {
		bookInfoDAO.deleteinstorebookhot(code);
	}
	/**
	 *  评分推荐
	 */
	public void instorebookhigh(Integer code) {
		bookInfoDAO.instorebookhigh(code);
	}
	/**
	 *  取消评分推荐
	 */
	public void deleteinstorebookhigh(Integer code) {
		bookInfoDAO.deleteinstorebookhigh(code);
	}
	/**
	 * 查询所有有折扣的图书
	 */
	public List<BookInfoBean> selectDiscStoreBook() {
		return bookInfoDAO.selectDiscStoreBook();
	}
	/**
	 *  修改折扣
	 */
	public void instorebookdiscupdate(BookInfoBean bookInfoBean) {
		bookInfoDAO.instorebookdiscupdate(bookInfoBean);
	}
	/**
	 * 查询所有NEW的图书
	 */
	public List<BookInfoBean> selectNewStoreBook() {
		return bookInfoDAO.selectNewStoreBook();
	}
	/**
	 * 查询所有HOT的图书
	 */
	public List<BookInfoBean> selectHotStoreBook() {
		return bookInfoDAO.selectHotStoreBook();
	}
	/**
	 * 查询所有HIGH的图书
	 */
	public List<BookInfoBean> selectHighStoreBook() {
		return bookInfoDAO.selectHighStoreBook();
	}
	/**
	 * 根据图书ID查询图书
	 */
	public BookInfoBean selectBookInfoByCode(Integer code) {
		return bookInfoDAO.selectBookInfoByCode(code);
	}
	/**
	 * 根据图书分类查询所有图书
	 */
	public List<BookInfoBean> selectInStoreBookByBookType(String bookType) {
		return bookInfoDAO.selectInStoreBookByBookType(bookType);
	}
	/**
	 * 根据图书名模糊查询所有图书
	 */
	public List<BookInfoBean> selectInStoreBookByBookName(String bookName) {
		return bookInfoDAO.selectInStoreBookByBookName(bookName);
	}
	
}
