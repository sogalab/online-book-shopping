package com.fanyongliang.book.service;

import java.util.List;

import com.fanyongliang.book.beans.BookInfoBean;
import com.fanyongliang.book.beans.BookOrderBean;
import com.fanyongliang.book.beans.BookOrderInfoBean;
import com.fanyongliang.book.beans.ShoppingCarBean;
import com.fanyongliang.book.bll.BookInfoBll;
import com.fanyongliang.book.bll.BookOrderBll;
import com.fanyongliang.book.bll.ShoppingCarBll;

/**
 * 图书订单业务拼接类
 * 
 * @author fanyongliang
 */
public class BookOrderService {
	private BookInfoBll bookInfoBll = new BookInfoBll();
	private ShoppingCarBll shoppingCarBll = new ShoppingCarBll();
	private BookOrderBll bookOrderBll = new BookOrderBll();

	/**
	 * 加入购物车
	 * 
	 * @param bookOrderBean
	 */
	public String addToCar(BookOrderBean bookOrderBean) {
		List<ShoppingCarBean> shoppingCarList = shoppingCarBll
				.selectToCar(bookOrderBean.getUserCode());
		// 检查库存是否充足
		for (ShoppingCarBean bean : shoppingCarList) {
			BookInfoBean bookInfoBean = bookInfoBll.selectBookInfoByCode(bean
					.getBookCode());
			if (bookInfoBean.getBookCount() < bean.getBookQuantity()) {
				return "库存不足！";
			}
		}
		// 记录订单信息
		bookOrderBll.addBookOrder(bookOrderBean);
		// 记录订单详细信息
		for (ShoppingCarBean bean : shoppingCarList) {
			BookInfoBean bookInfoBean = bookInfoBll.selectBookInfoByCode(bean
					.getBookCode());
			double payPrice = bookInfoBean.getBookPrice()
					* (1 - bookInfoBean.getBookDiscounts());
			BookOrderInfoBean bookOrderInfoBean = new BookOrderInfoBean(
					bookOrderBean.getOrderCode(), bean.getBookCode(),
					bean.getBookQuantity(), payPrice);
			bookOrderBll.insertBookOrderInfo(bookOrderInfoBean);
		}
		// 图书库存相应减少
		List<BookOrderInfoBean> bookOrderInfoList = bookOrderBll
				.selectBookOrderInfoByOrderCode(bookOrderBean.getOrderCode());
		for (BookOrderInfoBean orderInfoBean : bookOrderInfoList) {
			bookInfoBll.updateBookCountReduce(orderInfoBean.getBookCode(),
					orderInfoBean.getQuantity());
		}
		// 清空购物车
		shoppingCarBll.deleteAllToCar(bookOrderBean.getUserCode());
		return "付款成功！";
	}
}
