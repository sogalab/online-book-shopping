package com.fanyongliang.book.dao;

import java.util.List;

import com.fanyongliang.book.beans.BookCompareBean;

public interface IBookCompareMapper {
	/**
	 * 添加图书比较
	 * @param bookCompareBean
	 */
	public void addBookCompareInfo(BookCompareBean bookCompareBean);
	/**
	 * 查询某用户的图书比较信息
	 * 
	 * @param userCode
	 */
	public List<BookCompareBean> selectBookCompareInfoByUserCode(Integer userCode);
	/**
	 * 删除某一条图书比较信息
	 */
	public void deleteBookCompareInfoByCode(Integer code);
	/**
	 * 清空某用户图书比较信息
	 */
	public void deleteBookCompareInfoAll(Integer userCode);
	
}
