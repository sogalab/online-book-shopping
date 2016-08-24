package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 作者：
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 管理网站主页的控制器
 -------------------------------------------------------------------------*/

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

import com.fanyongliang.book.beans.AdvPhotoBean;
import com.fanyongliang.book.beans.BookInfoBean;
import com.fanyongliang.book.beans.NavListBean;
import com.fanyongliang.book.beans.ShoppingCarBean;
import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.AdvPhotoBll;
import com.fanyongliang.book.bll.BookInfoBll;
import com.fanyongliang.book.bll.LandAndRegistrationBll;
import com.fanyongliang.book.bll.NavListBll;
import com.fanyongliang.book.bll.ShoppingCarBll;


@Controller
@RequestMapping("/user")
public class BookController {
	private NavListBll NavListBll = new NavListBll();
	private AdvPhotoBll advPhotoBll = new AdvPhotoBll();
	private BookInfoBll bookInfoBll = new BookInfoBll();
	private LandAndRegistrationBll landAndRegistrationBll = new LandAndRegistrationBll();
	private ShoppingCarBll shoppingCarBll = new ShoppingCarBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(BookController.class);

	/**
	 * 直接跳转到网站主页
	 * 
	 * @return
	 * @throws MemcachedException
	 * @throws InterruptedException
	 * @throws TimeoutException
	 * @throws IOException
	 */
	@RequestMapping("index")
	public String index(Model model, HttpServletRequest request)
			throws IOException, TimeoutException, InterruptedException,
			MemcachedException {
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
						List<NavListBean> navList = NavListBll.selectNavListIsShow();
						model.addAttribute("navList", navList);
						List<AdvPhotoBean> advPhotoList = advPhotoBll.selectAllAdvPhoto();
						model.addAttribute("advPhotoList", advPhotoList);
						model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
						List<BookInfoBean> bookInfoListNew = bookInfoBll.selectNewStoreBook();
						model.addAttribute("bookInfoListNew", bookInfoListNew);
						List<BookInfoBean> bookInfoListHot = bookInfoBll.selectHotStoreBook();
						model.addAttribute("bookInfoListHot", bookInfoListHot);
						List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
						model.addAttribute("bookInfoListHigh", bookInfoListHigh);
						List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
						model.addAttribute("shoppingCarList", shoppingCarList);
						return "index";
					} else {
						UserInfoBean bean = (UserInfoBean) landAndRegistrationBll.getMem(value);
						if(bean.getRealName() == null){
							model.addAttribute("realName", "");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<AdvPhotoBean> advPhotoList = advPhotoBll.selectAllAdvPhoto();
							model.addAttribute("advPhotoList", advPhotoList);
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<BookInfoBean> bookInfoListNew = bookInfoBll.selectNewStoreBook();
							model.addAttribute("bookInfoListNew", bookInfoListNew);
							List<BookInfoBean> bookInfoListHot = bookInfoBll.selectHotStoreBook();
							model.addAttribute("bookInfoListHot", bookInfoListHot);
							List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
							model.addAttribute("bookInfoListHigh", bookInfoListHigh);
							List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "index";
						}else{
							model.addAttribute("realName", bean.getUserName());
							model.addAttribute("userCode", bean.getCode());
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<AdvPhotoBean> advPhotoList = advPhotoBll.selectAllAdvPhoto();
							model.addAttribute("advPhotoList", advPhotoList);
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<BookInfoBean> bookInfoListNew = bookInfoBll.selectNewStoreBook();
							model.addAttribute("bookInfoListNew", bookInfoListNew);
							List<BookInfoBean> bookInfoListHot = bookInfoBll.selectHotStoreBook();
							model.addAttribute("bookInfoListHot", bookInfoListHot);
							List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
							model.addAttribute("bookInfoListHigh", bookInfoListHigh);
							List<ShoppingCarBean> shoppingCarList = shoppingCarBll.selectToCar(bean.getCode());
							for (ShoppingCarBean shoppingCarBean : shoppingCarList) {
								shoppingCarBean.setBookInfoBean(bookInfoBll.selectBookInfoByCode(shoppingCarBean.getBookCode()));
							}
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "index";
						}
					}
				}else{
					logger.info("找不到名为accountuuid的Cookie!");
					model.addAttribute("realName", "");
					List<NavListBean> navList = NavListBll.selectNavListIsShow();
					model.addAttribute("navList", navList);
					List<AdvPhotoBean> advPhotoList = advPhotoBll.selectAllAdvPhoto();
					model.addAttribute("advPhotoList", advPhotoList);
					model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
					List<BookInfoBean> bookInfoListNew = bookInfoBll.selectNewStoreBook();
					model.addAttribute("bookInfoListNew", bookInfoListNew);
					List<BookInfoBean> bookInfoListHot = bookInfoBll.selectHotStoreBook();
					model.addAttribute("bookInfoListHot", bookInfoListHot);
					List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
					model.addAttribute("bookInfoListHigh", bookInfoListHigh);
					List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
					model.addAttribute("shoppingCarList", shoppingCarList);
					return "index";
				}
			}
		}else{
			logger.info("Cookie中不存在有效信息！");
			model.addAttribute("realName", "");
			List<NavListBean> navList = NavListBll.selectNavListIsShow();
			model.addAttribute("navList", navList);
			List<AdvPhotoBean> advPhotoList = advPhotoBll.selectAllAdvPhoto();
			model.addAttribute("advPhotoList", advPhotoList);
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			List<BookInfoBean> bookInfoListNew = bookInfoBll.selectNewStoreBook();
			model.addAttribute("bookInfoListNew", bookInfoListNew);
			List<BookInfoBean> bookInfoListHot = bookInfoBll.selectHotStoreBook();
			model.addAttribute("bookInfoListHot", bookInfoListHot);
			List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
			model.addAttribute("bookInfoListHigh", bookInfoListHigh);
			List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
			model.addAttribute("shoppingCarList", shoppingCarList);
			return "index";
		}
		List<NavListBean> navList = NavListBll.selectNavListIsShow();
		model.addAttribute("navList", navList);
		List<AdvPhotoBean> advPhotoList = advPhotoBll.selectAllAdvPhoto();
		model.addAttribute("advPhotoList", advPhotoList);
		model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
		List<BookInfoBean> bookInfoListNew = bookInfoBll.selectNewStoreBook();
		model.addAttribute("bookInfoListNew", bookInfoListNew);
		List<BookInfoBean> bookInfoListHot = bookInfoBll.selectHotStoreBook();
		model.addAttribute("bookInfoListHot", bookInfoListHot);
		List<BookInfoBean> bookInfoListHigh = bookInfoBll.selectHighStoreBook();
		model.addAttribute("bookInfoListHigh", bookInfoListHigh);
		List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
		model.addAttribute("shoppingCarList", shoppingCarList);
		return "index";
	}

}
