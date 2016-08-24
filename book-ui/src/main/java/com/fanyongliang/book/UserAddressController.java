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
import com.fanyongliang.book.bll.UserInfoBll;

/**
 * 用户收货地址管理
 */
@Controller
@RequestMapping("user")
public class UserAddressController {
	private NavListBll NavListBll = new NavListBll();
	private BookInfoBll bookInfoBll = new BookInfoBll();
	private BookReviewsBll bookReviewsBll = new BookReviewsBll();
	private LandAndRegistrationBll landAndRegistrationBll = new LandAndRegistrationBll();
	private BookCompareBll bookCompareBll = new BookCompareBll();
	private ShoppingCarBll shoppingCarBll = new ShoppingCarBll();
	private BookOrderBll bookOrderBll = new BookOrderBll();
	private UserInfoBll userInfoBll = new UserInfoBll();
	private UserAddressBll userAddressBll = new UserAddressBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserRegisterController.class);
	/**
	 * 更新收货地址
	 */
	@RequestMapping("updateAddress")
	@ResponseBody
	public void updateAddress(UserAddressBean userAddressBean) {
		userAddressBll.updateAddress(userAddressBean);
	}
	
	/**
	 * 查找收货地址
	 */
	@RequestMapping("selectAddress")
	public String selcetUserInfo(Integer userCode,Model model,HttpServletRequest request) 
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
						List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
						model.addAttribute("shoppingCarList", shoppingCarList);
						List<UserAddressBean> addressList = userAddressBll.getAddress(userCode);
						model.addAttribute("addressList", addressList);
						return "user/user_address";
					} else {
						UserInfoBean bean = (UserInfoBean) landAndRegistrationBll.getMem(value);
						if(bean.getRealName() == null){
							model.addAttribute("realName", "");
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
							model.addAttribute("shoppingCarList", shoppingCarList);
							List<UserAddressBean> addressList = userAddressBll.getAddress(userCode);
							model.addAttribute("addressList", addressList);
							return "user/user_address";
						}else{
							model.addAttribute("realName", bean.getUserName());
							model.addAttribute("userName", bean.getUserName());
							model.addAttribute("userCode", bean.getCode());
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<ShoppingCarBean> shoppingCarList = shoppingCarBll.selectToCar(bean.getCode());
							for (ShoppingCarBean shoppingCarBean : shoppingCarList) {
								shoppingCarBean.setBookInfoBean(bookInfoBll.selectBookInfoByCode(shoppingCarBean.getBookCode()));
							}
							model.addAttribute("shoppingCarList", shoppingCarList);
							UserInfoBean userInfoBean = userInfoBll.selectUserByName(bean.getUserName());
							model.addAttribute("userInfoBean", userInfoBean);
							List<UserAddressBean> addressList = userAddressBll.getAddress(userCode);
							model.addAttribute("addressList", addressList);
							return "user/user_address";
						}
					}
				}else{
					model.addAttribute("realName", "");
					model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
					List<NavListBean> navList = NavListBll.selectNavListIsShow();
					model.addAttribute("navList", navList);
					List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
					model.addAttribute("shoppingCarList", shoppingCarList);
					List<UserAddressBean> addressList = userAddressBll.getAddress(userCode);
					model.addAttribute("addressList", addressList);
					return "user/user_address";
				}
			}
		}else{
			logger.info("Cookie中不存在有效信息！");
			model.addAttribute("realName", "");
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			List<NavListBean> navList = NavListBll.selectNavListIsShow();
			model.addAttribute("navList", navList);
			List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
			model.addAttribute("shoppingCarList", shoppingCarList);
			List<UserAddressBean> addressList = userAddressBll.getAddress(userCode);
			model.addAttribute("addressList", addressList);
			return "user/user_address";
		}
		model.addAttribute("realName", "");
		model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
		List<NavListBean> navList = NavListBll.selectNavListIsShow();
		model.addAttribute("navList", navList);
		List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
		model.addAttribute("shoppingCarList", shoppingCarList);
		List<UserAddressBean> addressList = userAddressBll.getAddress(userCode);
		model.addAttribute("addressList", addressList);
		return "user/user_address";
	}	
	/**
	 * 删除收货地址
	 */
	@RequestMapping("deleteAddress")
	@ResponseBody
	public void deleteAddress(Integer code) {
		userAddressBll.deleteAddredd(code);
	}
	/**
	 * 增加收货地址
	 */
	@RequestMapping("addAddress")
	@ResponseBody
	public void addAddress(UserAddressBean userAddressBean) {
		userAddressBll.addAddress(userAddressBean); 
	}
}
