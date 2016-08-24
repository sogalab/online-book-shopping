package com.fanyongliang.book;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanyongliang.book.beans.BookOrderBean;
import com.fanyongliang.book.bll.BookCompareBll;
import com.fanyongliang.book.bll.BookInfoBll;
import com.fanyongliang.book.bll.BookOrderBll;
import com.fanyongliang.book.bll.BookReviewsBll;
import com.fanyongliang.book.bll.LandAndRegistrationBll;
import com.fanyongliang.book.bll.NavListBll;
import com.fanyongliang.book.bll.ShoppingCarBll;
import com.fanyongliang.book.service.BookOrderService;
/**
 * 用户订单处理控制器
 */
@Controller
@RequestMapping("/user")
public class UserBookOrderController {
	private NavListBll NavListBll = new NavListBll();
	private BookInfoBll bookInfoBll = new BookInfoBll();
	private BookReviewsBll bookReviewsBll = new BookReviewsBll();
	private LandAndRegistrationBll landAndRegistrationBll = new LandAndRegistrationBll();
	private BookCompareBll bookCompareBll = new BookCompareBll();
	private ShoppingCarBll shoppingCarBll = new ShoppingCarBll();
	private BookOrderBll bookOrderBll = new BookOrderBll();
	private BookOrderService bookOrderService = new BookOrderService();
	/**
	 * 删除订单
	 * @param orderCode
	 */
	@RequestMapping("/deleteOrder")
	@ResponseBody
	public void deleteOrder(Integer orderCode){
		bookOrderBll.deleteBookOrder(orderCode);	
	}
	/**
	 * 增加订单
	 * @param userCode
	 * @param total
	 * @param userAddress
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/addOrder", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String payForCar(Integer userCode, double total, String userAddress) throws UnsupportedEncodingException{
		userAddress = new String(userAddress.getBytes("iso8859-1"),"utf-8");
		BookOrderBean bookOrderBean = new BookOrderBean(new Date(), 1,total, userAddress,userCode);
		String message = bookOrderService.addToCar(bookOrderBean);
		return message;
	}
	/**
	 * 根据订单的状态查找订单类表
	 * @param orderCode
	 */
	@RequestMapping("selectOrder")
	public void selectOrder(Integer status, Model model){
		List<BookOrderBean> bookOrderList = bookOrderBll.selectBookOrderByStatus(status);
		model.addAttribute("bookOrderList", bookOrderList);
	}
	/**
	 * 更改订单的状态
	 * @param orderCode
	 */
	@RequestMapping("updateOrderStatus")
	@ResponseBody
	public void updateOrderStatus(Integer orderCode){
		bookOrderBll.updateBookOrderStatus(orderCode, 3);
	}
}
