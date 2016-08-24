package com.fanyongliang.book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import com.fanyongliang.book.beans.NavListBean;
import com.fanyongliang.book.beans.ShoppingCarBean;
import com.fanyongliang.book.beans.UserAddressBean;
import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.BookCompareBll;
import com.fanyongliang.book.bll.BookInfoBll;
import com.fanyongliang.book.bll.BookOrderBll;
import com.fanyongliang.book.bll.BookReviewsBll;
import com.fanyongliang.book.bll.LandAndRegistrationBll;
import com.fanyongliang.book.bll.NavListBll;
import com.fanyongliang.book.bll.ShoppingCarBll;
import com.fanyongliang.book.bll.UserAddressBll;

/**
 * 购物车控制器
 */
@Controller
@RequestMapping("/user")
public class UserShoppingCarController {
	private NavListBll NavListBll = new NavListBll();
	private BookInfoBll bookInfoBll = new BookInfoBll();
	private BookReviewsBll bookReviewsBll = new BookReviewsBll();
	private LandAndRegistrationBll landAndRegistrationBll = new LandAndRegistrationBll();
	private BookCompareBll bookCompareBll = new BookCompareBll();
	private ShoppingCarBll shoppingCarBll = new ShoppingCarBll();
	private BookOrderBll bookOrderBll = new BookOrderBll();
	private UserAddressBll UserAddressBll = new UserAddressBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserRegisterController.class);
	/**
	 * 向购物车中增加
	 * @param shoppingCarBean
	 */
	@RequestMapping("addShoppingCar")
	@ResponseBody
	public void addShoppingCar(Integer userCode,Integer bookCode,Integer bookQuantity){
		ShoppingCarBean shoppingCarBean = new ShoppingCarBean();
		shoppingCarBean.setUserCode(userCode);
		shoppingCarBean.setBookCode(bookCode);
		shoppingCarBean.setBookQuantity(bookQuantity);
		shoppingCarBll.insertToCar(shoppingCarBean);
	}
	/**
	 * 将购物车 中的数据删除
	 * @param carCode
	 */
	@RequestMapping("removeShoppingCar")
	@ResponseBody
	public void removeShoppingCar(Integer carCode){
		shoppingCarBll.deleteToCar(carCode);
	}
	/**
	 * 删除购物中的所有数据
	 * @param userCode
	 */
	@RequestMapping("removeAllShoppingCar")
	@ResponseBody
	public void removeAllShoppingCar(Integer userCode){
		shoppingCarBll.deleteAllToCar(userCode);
	}
	/**
	 * 更新购物车中的数据
	 * @param shoppingCarBean
	 */
	@RequestMapping("updateAllShoppingCar")
	@ResponseBody
	public void updateAllShoppingCar(ShoppingCarBean shoppingCarBean){
		shoppingCarBll.updateCarByCode(shoppingCarBean);
	}
	/**
	 * 获得购物车全部信息
	 * @param userCode
	 * @param model
	 * @return
	 */
	@RequestMapping("getAllShoppingCar")
	public String bookDetailed(Integer userCode,Model model,HttpServletRequest request) 
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
						List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
						model.addAttribute("bookInfoListHigh", bookInfoListHigh);
						List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
						model.addAttribute("shoppingCarList", shoppingCarList);
						return "user/shopping_cart";
					} else {
						UserInfoBean bean = (UserInfoBean) landAndRegistrationBll.getMem(value);
						if(bean.getRealName() == null){
							model.addAttribute("realName", "");
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
							model.addAttribute("bookInfoListHigh", bookInfoListHigh);
							List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "user/shopping_cart";
						}else{
							model.addAttribute("realName", bean.getUserName());
							model.addAttribute("userCode", bean.getCode());
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
							model.addAttribute("bookInfoListHigh", bookInfoListHigh);
							List<ShoppingCarBean> shoppingCarList = shoppingCarBll.selectToCar(userCode);
							for (ShoppingCarBean shoppingCarBean : shoppingCarList) {
								shoppingCarBean.setBookInfoBean(bookInfoBll.selectBookInfoByCode(shoppingCarBean.getBookCode()));
							}
							model.addAttribute("shoppingCarList", shoppingCarList);
							List<UserAddressBean> userAddressList =  UserAddressBll.getAddress(bean.getCode());
							model.addAttribute("userAddressList", userAddressList);
							return "user/shopping_cart";
						}
					}
				}else{
					model.addAttribute("realName", "");
					model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
					List<NavListBean> navList = NavListBll.selectNavListIsShow();
					model.addAttribute("navList", navList);
					List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
					model.addAttribute("bookInfoListHigh", bookInfoListHigh);
					List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
					model.addAttribute("shoppingCarList", shoppingCarList);
					return "user/shopping_cart";
				}
			}
		}else{
			logger.info("Cookie中不存在有效信息！");
			model.addAttribute("realName", "");
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			List<NavListBean> navList = NavListBll.selectNavListIsShow();
			model.addAttribute("navList", navList);
			List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
			model.addAttribute("bookInfoListHigh", bookInfoListHigh);
			List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
			model.addAttribute("shoppingCarList", shoppingCarList);
			return "user/shopping_cart";
		}
		model.addAttribute("realName", "");
		model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
		List<NavListBean> navList = NavListBll.selectNavListIsShow();
		model.addAttribute("navList", navList);
		List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
		model.addAttribute("bookInfoListHigh", bookInfoListHigh);
		List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
		model.addAttribute("shoppingCarList", shoppingCarList);
		return "user/shopping_cart";
	}	
}
