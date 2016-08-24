package com.fanyongliang.book.dao;

import java.util.List;

import com.fanyongliang.book.beans.BookOrderInfoBean;

public interface IBookOrderInfoMapper {
	/**
	 * 删除订单详细信息
	 * @param orderCode
	 */
	public void deleteBookOrderInfo(Integer orderCode);
	/**
	 * 插入数据
	 * @param bookOrderInfoBean
	 */
	public void insertBookOrderInfo(BookOrderInfoBean bookOrderInfoBean);
	/**
	 * 查找书本订单数据靠订单编号
	 * @param orderCode
	 * @return
	 */
	public List<BookOrderInfoBean> selectBookOrderInfoByOrderCode(Integer orderCode);
}
