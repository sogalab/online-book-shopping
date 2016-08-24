package com.fanyongliang.book.bll;

import java.util.List;

import com.fanyongliang.book.beans.BookOrderBean;
import com.fanyongliang.book.beans.BookOrderInfoBean;
import com.fanyongliang.book.dao.BookInfoDAO;
import com.fanyongliang.book.dao.BookOrderDAO;
import com.fanyongliang.book.dao.BookOrderInfoDAO;

/**
 * 图书订单业务逻辑类
 */
public class BookOrderBll {
	private BookOrderDAO bookOrderDAO = new BookOrderDAO();
	private BookOrderInfoDAO bookOrderInfoDAO = new BookOrderInfoDAO();
	private BookInfoDAO bookInfoDAO = new BookInfoDAO();
	/**
	 * 增加订单
	 * @param bookOrderBean
	 */
	public void addBookOrder(BookOrderBean bookOrderBean){	
		bookOrderDAO.insertBookOrder(bookOrderBean);
		
	}
	/**
	 * 查找订单按照用户
	 */
	public List<BookOrderBean> selectBookOrderByUserCode(Integer usercode){
		return bookOrderDAO.selectBookOrderByUserCode(usercode);
	}
	/**
	 * 查找订单按照状态
	 */
	public List<BookOrderBean> selectBookOrderByStatus(Integer status){
		return bookOrderDAO.selectBookOrderByStatus(status);
	}
	/**
	 * 用户查找自己的订单
	 */
	public List<BookOrderBean> selectUserBookOrderByStatus(Integer status ,Integer userCode){
		return bookOrderDAO.selectUserBookOrderByStatus(status, userCode);
	}
	/**
	 * 更新订单发货状态
	 * @param orderCode
	 */
	public void updateBookOrderStatus(Integer orderCode ,Integer status){
		bookOrderDAO.updateBookOrderStatus(orderCode, status);
	}
	/**
	 * 向BookOrderInfo表插入数据
	 * @param bookOrderInfoBean
	 */
	public void insertBookOrderInfo(BookOrderInfoBean bookOrderInfoBean){
		bookOrderInfoDAO.insertBookOrderInfo(bookOrderInfoBean);
	}
	/**
	 * 查找书本订单数据靠订单编号
	 * @param orderCode
	 * @return
	 */
	public List<BookOrderInfoBean> selectBookOrderInfoByOrderCode(Integer orderCode){
		return bookOrderInfoDAO.selectBookOrderInfoByOrderCode(orderCode);
	}
	/**
	 * 删除购书订单
	 * @param orderCode
	 */
	public void deleteBookOrder(Integer orderCode){
		bookOrderDAO.deleteBookOrder(orderCode);
		bookOrderInfoDAO.deleteBookOrderInfo(orderCode);
	}
}
