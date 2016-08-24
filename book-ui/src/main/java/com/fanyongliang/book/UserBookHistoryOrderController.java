package com.fanyongliang.book;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanyongliang.book.beans.BookInfoBean;
import com.fanyongliang.book.beans.BookOrderBean;
import com.fanyongliang.book.beans.BookOrderInfoBean;
import com.fanyongliang.book.beans.NavListBean;
import com.fanyongliang.book.beans.ShoppingCarBean;
import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.BookCompareBll;
import com.fanyongliang.book.bll.BookInfoBll;
import com.fanyongliang.book.bll.BookOrderBll;
import com.fanyongliang.book.bll.BookReviewsBll;
import com.fanyongliang.book.bll.LandAndRegistrationBll;
import com.fanyongliang.book.bll.NavListBll;
import com.fanyongliang.book.bll.ShoppingCarBll;

/**
 * 历史订单控制器
 */
@Controller
@RequestMapping("/user")
public class UserBookHistoryOrderController {
	private NavListBll NavListBll = new NavListBll();
	private BookInfoBll bookInfoBll = new BookInfoBll();
	private BookReviewsBll bookReviewsBll = new BookReviewsBll();
	private LandAndRegistrationBll landAndRegistrationBll = new LandAndRegistrationBll();
	private BookCompareBll bookCompareBll = new BookCompareBll();
	private ShoppingCarBll shoppingCarBll = new ShoppingCarBll();
	private BookOrderBll bookOrderBll = new BookOrderBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserRegisterController.class);
	/**
	 * 获得历史订单全部信息
	 * @param userCode
	 * @param model
	 * @return
	 */
	@RequestMapping("historyOrder")
	public String historyOrder(Integer userCode,Model model,HttpServletRequest request) 
			throws IOException, TimeoutException, InterruptedException,
			MemcachedException{
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			logger.info("Cookie数量:" + cookies.length);
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("accountuuid")) {
					logger.info("-------true & false-----:"
							+ cookie.getName().equals("accountuuid"));
					String value = cookie.getValue();
					logger.info("value:"+value);
					if (landAndRegistrationBll.getMem(value) == null) {
						logger.info("memcache中不存在信息!");
						model.addAttribute("realName", "");
						model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
						List<NavListBean> navList = NavListBll.selectNavListIsShow();
						model.addAttribute("navList", navList);
						List<BookOrderInfoBean> bookOrderInfoList = new ArrayList<BookOrderInfoBean>();
						model.addAttribute("bookOrderInfoList", bookOrderInfoList);
						List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
						model.addAttribute("shoppingCarList", shoppingCarList);
						return "user/history_cart";
					} else {
						UserInfoBean bean = (UserInfoBean) landAndRegistrationBll.getMem(value);
						if(bean.getRealName() == null){
							model.addAttribute("realName", "");
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookOrderInfoBean> bookOrderInfoList = new ArrayList<BookOrderInfoBean>();
							model.addAttribute("bookOrderInfoList", bookOrderInfoList);
							List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "user/history_cart";
						}else{
							model.addAttribute("realName", bean.getUserName());
							model.addAttribute("userCode", bean.getCode());
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookOrderBean> bookOrderList = bookOrderBll.selectBookOrderByUserCode(bean.getCode());
							if(bookOrderList != null){
								for(BookOrderBean bookOrderBean:bookOrderList){
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									bookOrderBean.setOrderTimeString(sdf.format(bookOrderBean.getOrderTime()));
								}
							}
							List<BookOrderInfoBean> bookOrderInfoList = new ArrayList<BookOrderInfoBean>();
							for (BookOrderBean bookOrderBean : bookOrderList) {
								List<BookOrderInfoBean> bookOrderInfoListnew = bookOrderBll.selectBookOrderInfoByOrderCode(bookOrderBean.getOrderCode());
								for (BookOrderInfoBean bookOrderInfoBean : bookOrderInfoListnew){
									bookOrderInfoBean.setBookInfoBean(bookInfoBll.selectBookInfoByCode(bookOrderInfoBean.getBookCode()));
									bookOrderInfoBean.setOrderTimeString(bookOrderBean.getOrderTimeString());
									bookOrderInfoBean.setOrderStatus(bookOrderBean.getOrderStatus());
									bookOrderInfoList.add(bookOrderInfoBean);
								}
							}
							model.addAttribute("bookOrderList", bookOrderList);
							model.addAttribute("bookOrderInfoList", bookOrderInfoList);
							List<ShoppingCarBean> shoppingCarList = shoppingCarBll.selectToCar(bean.getCode());
							for (ShoppingCarBean shoppingCarBean : shoppingCarList) {
								shoppingCarBean.setBookInfoBean(bookInfoBll.selectBookInfoByCode(shoppingCarBean.getBookCode()));
							}
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "user/history_cart";
						}
					}
				}else{
					model.addAttribute("realName", "");
					model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
					List<NavListBean> navList = NavListBll.selectNavListIsShow();
					model.addAttribute("navList", navList);
					List<BookOrderInfoBean> bookOrderInfoList = new ArrayList<BookOrderInfoBean>();
					model.addAttribute("bookOrderInfoList", bookOrderInfoList);
					List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
					model.addAttribute("shoppingCarList", shoppingCarList);
					return "user/history_cart";
				}
			}
		}else{
			logger.info("Cookie中不存在有效信息！");
			model.addAttribute("realName", "");
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			List<NavListBean> navList = NavListBll.selectNavListIsShow();
			model.addAttribute("navList", navList);
			List<BookOrderInfoBean> bookOrderInfoList = new ArrayList<BookOrderInfoBean>();
			model.addAttribute("bookOrderInfoList", bookOrderInfoList);
			List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
			model.addAttribute("shoppingCarList", shoppingCarList);
			return "user/history_cart";
		}
		model.addAttribute("realName", "");
		model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
		List<NavListBean> navList = NavListBll.selectNavListIsShow();
		model.addAttribute("navList", navList);
		List<BookOrderInfoBean> bookOrderInfoList = new ArrayList<BookOrderInfoBean>();
		model.addAttribute("bookOrderInfoList", bookOrderInfoList);
		List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
		model.addAttribute("shoppingCarList", shoppingCarList);
		return "user/history_cart";
	}	
}
