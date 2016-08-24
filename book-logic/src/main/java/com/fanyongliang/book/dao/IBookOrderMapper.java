package com.fanyongliang.book.dao;

import java.util.List;

import com.fanyongliang.book.beans.BookOrderBean;

public interface IBookOrderMapper { 
	/**
	 * 删除购书订单
	 * @param orderCode
	 */
	public void deleteBookOrder(Integer orderCode);
	/**
	 * 插入BookOrderBean
	 * @param bookOrderBean
	 */
	public void insertBookOrder(BookOrderBean bookOrderBean);
	/**
	 * 查找订单按用户
	 */
	public List<BookOrderBean> selectBookOrderByUserCode(Integer userCode);
	/**
	 * 查找订单按照状态
	 */
	public List<BookOrderBean> selectBookOrderByStatus(Integer status);
	/**
	 * 用户查找自己的订单
	 */
	public List<BookOrderBean> selectUserBookOrderByStatus(Integer status ,Integer userCode);
	/**
	 * 更新订单发货状态
	 * @param orderCode
	 */
	public void updateBookOrderStatus(Integer orderCode,Integer status);

}
