package com.fanyongliang.book.bll;

import java.util.List;

import com.fanyongliang.book.beans.BookCompareBean;
import com.fanyongliang.book.dao.BookCompareDAO;


/**
 * 比较信息管理
 */
public class BookCompareBll {
	BookCompareDAO bookCompareDAO = new BookCompareDAO();
	
	/**
	 * 添加图书比较
	 * @param bookCompareBean
	 */
	public void addBookCompareInfo(BookCompareBean bookCompareBean) {
		bookCompareDAO.addBookCompareInfo(bookCompareBean);
	}
	/**
	 * 查询某用户的图书比较信息
	 * 
	 * @param userCode
	 */
	public List<BookCompareBean> selectBookCompareInfoByUserCode(Integer userCode){
		return bookCompareDAO.selectBookCompareInfoByUserCode(userCode);
	}
	/**
	 * 删除某一条图书比较信息
	 */
	public void deleteBookCompareInfoByCode(Integer code){
		bookCompareDAO.deleteBookCompareInfoByCode(code);
	}
	/**
	 * 清空某用户图书比较信息
	 */
	public void deleteBookCompareInfoAll(Integer userCode){
		bookCompareDAO.deleteBookCompareInfoAll(userCode);
	}
}
