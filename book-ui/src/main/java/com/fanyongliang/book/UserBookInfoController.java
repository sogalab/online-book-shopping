package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 作者：
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 图书分类展示
 -------------------------------------------------------------------------*/



import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import com.fanyongliang.book.beans.BookCompareBean;
import com.fanyongliang.book.beans.BookInfoBean;
import com.fanyongliang.book.beans.BookReviewsBean;
import com.fanyongliang.book.beans.NavListBean;
import com.fanyongliang.book.beans.ShoppingCarBean;
import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.BookCompareBll;
import com.fanyongliang.book.bll.BookInfoBll;
import com.fanyongliang.book.bll.BookReviewsBll;
import com.fanyongliang.book.bll.LandAndRegistrationBll;
import com.fanyongliang.book.bll.NavListBll;
import com.fanyongliang.book.bll.ShoppingCarBll;



@Controller
@RequestMapping("/user")
public class UserBookInfoController {
	private NavListBll NavListBll = new NavListBll();
	private BookInfoBll bookInfoBll = new BookInfoBll();
	private BookCompareBll bookCompareBll = new BookCompareBll();
	private LandAndRegistrationBll landAndRegistrationBll = new LandAndRegistrationBll();
	private ShoppingCarBll shoppingCarBll = new ShoppingCarBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserBookDetailedController.class);


	/**
	 *  导航分类检索Grid
	 * 
	 * @return
	 */
	@RequestMapping("bookInfoGrid")
	public String bookInfoGrid(String bookType,Model model,HttpServletRequest request) 
			throws IOException, TimeoutException, InterruptedException,
			MemcachedException{
		bookType = new String(bookType.getBytes("iso8859-1"),"utf-8");
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
						List<BookInfoBean> bookInfoList = null;
						if(bookType.equals("所有分类")){
							bookInfoList = bookInfoBll.selectInStoreBook();
							model.addAttribute("bookInfoList", bookInfoList);
						}else if(bookType.equals("新书上架")){
							bookInfoList = bookInfoBll.selectNewStoreBook();
							model.addAttribute("bookInfoList", bookInfoList);
						}else if(bookType.equals("畅销图书")){
							bookInfoList = bookInfoBll.selectHotStoreBook();
							model.addAttribute("bookInfoList", bookInfoList);
						}else if(bookType.equals("人气图书")){
							bookInfoList = bookInfoBll.selectHighStoreBook();
							model.addAttribute("bookInfoList", bookInfoList);
						}else{
							bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
							model.addAttribute("bookInfoList", bookInfoList);
						}
						model.addAttribute("bookType", bookType);
						List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
						model.addAttribute("bookCompareList", bookCompareList);
						List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
						model.addAttribute("shoppingCarList", shoppingCarList);
						return "user/catalog_grid";
					} else {
						UserInfoBean bean = (UserInfoBean) landAndRegistrationBll.getMem(value);
						if(bean.getRealName() == null){
							model.addAttribute("realName", "");
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookInfoBean> bookInfoList = null;
							if(bookType.equals("所有分类")){
								bookInfoList = bookInfoBll.selectInStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("新书上架")){
								bookInfoList = bookInfoBll.selectNewStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("畅销图书")){
								bookInfoList = bookInfoBll.selectHotStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("人气图书")){
								bookInfoList = bookInfoBll.selectHighStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else{
								bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
								model.addAttribute("bookInfoList", bookInfoList);
							}
							model.addAttribute("bookType", bookType);
							List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
							model.addAttribute("bookCompareList", bookCompareList);
							List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "user/catalog_grid";
						}else{
							model.addAttribute("realName", bean.getUserName());
							model.addAttribute("userCode", bean.getCode());
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookInfoBean> bookInfoList = null;
							if(bookType.equals("所有分类")){
								bookInfoList = bookInfoBll.selectInStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("新书上架")){
								bookInfoList = bookInfoBll.selectNewStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("畅销图书")){
								bookInfoList = bookInfoBll.selectHotStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("人气图书")){
								bookInfoList = bookInfoBll.selectHighStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else{
								bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
								model.addAttribute("bookInfoList", bookInfoList);
							}
							model.addAttribute("bookType", bookType);
							List<BookCompareBean> bookCompareList = bookCompareBll.selectBookCompareInfoByUserCode(bean.getCode());
							model.addAttribute("bookCompareList", bookCompareList);
							List<ShoppingCarBean> shoppingCarList = shoppingCarBll.selectToCar(bean.getCode());
							for (ShoppingCarBean shoppingCarBean : shoppingCarList) {
								shoppingCarBean.setBookInfoBean(bookInfoBll.selectBookInfoByCode(shoppingCarBean.getBookCode()));
							}
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "user/catalog_grid";
						}
					}
				}else{
					logger.info("找不到名为accountuuid的Cookie!");
					model.addAttribute("realName", "");
					model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
					List<NavListBean> navList = NavListBll.selectNavListIsShow();
					model.addAttribute("navList", navList);
					List<BookInfoBean> bookInfoList = null;
					if(bookType.equals("所有分类")){
						bookInfoList = bookInfoBll.selectInStoreBook();
						model.addAttribute("bookInfoList", bookInfoList);
					}else if(bookType.equals("新书上架")){
						bookInfoList = bookInfoBll.selectNewStoreBook();
						model.addAttribute("bookInfoList", bookInfoList);
					}else if(bookType.equals("畅销图书")){
						bookInfoList = bookInfoBll.selectHotStoreBook();
						model.addAttribute("bookInfoList", bookInfoList);
					}else if(bookType.equals("人气图书")){
						bookInfoList = bookInfoBll.selectHighStoreBook();
						model.addAttribute("bookInfoList", bookInfoList);
					}else{
						bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
						model.addAttribute("bookInfoList", bookInfoList);
					}
					model.addAttribute("bookType", bookType);
					List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
					model.addAttribute("bookCompareList", bookCompareList);
					List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
					model.addAttribute("shoppingCarList", shoppingCarList);
					return "user/catalog_grid";
				}
			}
		}else{
			logger.info("Cookie中不存在有效信息！");
			model.addAttribute("realName", "");
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			List<NavListBean> navList = NavListBll.selectNavListIsShow();
			model.addAttribute("navList", navList);
			List<BookInfoBean> bookInfoList = null;
			if(bookType.equals("所有分类")){
				bookInfoList = bookInfoBll.selectInStoreBook();
				model.addAttribute("bookInfoList", bookInfoList);
			}else if(bookType.equals("新书上架")){
				bookInfoList = bookInfoBll.selectNewStoreBook();
				model.addAttribute("bookInfoList", bookInfoList);
			}else if(bookType.equals("畅销图书")){
				bookInfoList = bookInfoBll.selectHotStoreBook();
				model.addAttribute("bookInfoList", bookInfoList);
			}else if(bookType.equals("人气图书")){
				bookInfoList = bookInfoBll.selectHighStoreBook();
				model.addAttribute("bookInfoList", bookInfoList);
			}else{
				bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
				model.addAttribute("bookInfoList", bookInfoList);
			}
			model.addAttribute("bookType", bookType);
			List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
			model.addAttribute("bookCompareList", bookCompareList);
			List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
			model.addAttribute("shoppingCarList", shoppingCarList);
			return "user/catalog_grid";
		}
		model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
		List<NavListBean> navList = NavListBll.selectNavListIsShow();
		model.addAttribute("navList", navList);
		List<BookInfoBean> bookInfoList = null;
		if(bookType.equals("所有分类")){
			bookInfoList = bookInfoBll.selectInStoreBook();
			model.addAttribute("bookInfoList", bookInfoList);
		}else if(bookType.equals("新书上架")){
			bookInfoList = bookInfoBll.selectNewStoreBook();
			model.addAttribute("bookInfoList", bookInfoList);
		}else if(bookType.equals("畅销图书")){
			bookInfoList = bookInfoBll.selectHotStoreBook();
			model.addAttribute("bookInfoList", bookInfoList);
		}else if(bookType.equals("人气图书")){
			bookInfoList = bookInfoBll.selectHighStoreBook();
			model.addAttribute("bookInfoList", bookInfoList);
		}else{
			bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
			model.addAttribute("bookInfoList", bookInfoList);
		}
		model.addAttribute("bookType", bookType);
		List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
		model.addAttribute("bookCompareList", bookCompareList);
		List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
		model.addAttribute("shoppingCarList", shoppingCarList);
		return "user/catalog_grid";
	}
	
	/**
	 * 导航分类检索List
	 * 
	 * @return
	 */
	@RequestMapping("bookInfoList")
	public String bookInfoList(String bookType,Model model,HttpServletRequest request) 
			throws IOException, TimeoutException, InterruptedException,
			MemcachedException{
		bookType = new String(bookType.getBytes("iso8859-1"),"utf-8");
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
						List<BookInfoBean> bookInfoList = null;
						if(bookType.equals("所有分类")){
							bookInfoList = bookInfoBll.selectInStoreBook();
							model.addAttribute("bookInfoList", bookInfoList);
						}else if(bookType.equals("新书上架")){
							bookInfoList = bookInfoBll.selectNewStoreBook();
							model.addAttribute("bookInfoList", bookInfoList);
						}else if(bookType.equals("畅销图书")){
							bookInfoList = bookInfoBll.selectHotStoreBook();
							model.addAttribute("bookInfoList", bookInfoList);
						}else if(bookType.equals("人气图书")){
							bookInfoList = bookInfoBll.selectHighStoreBook();
							model.addAttribute("bookInfoList", bookInfoList);
						}else{
							bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
							model.addAttribute("bookInfoList", bookInfoList);
						}
						model.addAttribute("bookType", bookType);
						List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
						model.addAttribute("bookCompareList", bookCompareList);
						List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
						model.addAttribute("shoppingCarList", shoppingCarList);
						return "user/catalog_list";
					} else {
						UserInfoBean bean = (UserInfoBean) landAndRegistrationBll.getMem(value);
						if(bean.getRealName() == null){
							model.addAttribute("realName", "");
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookInfoBean> bookInfoList = null;
							if(bookType.equals("所有分类")){
								bookInfoList = bookInfoBll.selectInStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("新书上架")){
								bookInfoList = bookInfoBll.selectNewStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("畅销图书")){
								bookInfoList = bookInfoBll.selectHotStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("人气图书")){
								bookInfoList = bookInfoBll.selectHighStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else{
								bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
								model.addAttribute("bookInfoList", bookInfoList);
							}
							model.addAttribute("bookType", bookType);
							List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
							model.addAttribute("bookCompareList", bookCompareList);
							List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "user/catalog_list";
						}else{
							model.addAttribute("realName", bean.getUserName());
							model.addAttribute("userCode", bean.getCode());
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookInfoBean> bookInfoList = null;
							if(bookType.equals("所有分类")){
								bookInfoList = bookInfoBll.selectInStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("新书上架")){
								bookInfoList = bookInfoBll.selectNewStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("畅销图书")){
								bookInfoList = bookInfoBll.selectHotStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else if(bookType.equals("人气图书")){
								bookInfoList = bookInfoBll.selectHighStoreBook();
								model.addAttribute("bookInfoList", bookInfoList);
							}else{
								bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
								model.addAttribute("bookInfoList", bookInfoList);
							}
							model.addAttribute("bookType", bookType);
							List<BookCompareBean> bookCompareList = bookCompareBll.selectBookCompareInfoByUserCode(bean.getCode());
							model.addAttribute("bookCompareList", bookCompareList);
							List<ShoppingCarBean> shoppingCarList = shoppingCarBll.selectToCar(bean.getCode());
							for (ShoppingCarBean shoppingCarBean : shoppingCarList) {
								shoppingCarBean.setBookInfoBean(bookInfoBll.selectBookInfoByCode(shoppingCarBean.getBookCode()));
							}
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "user/catalog_list";
						}
					}
				}else{
					logger.info("找不到名为accountuuid的Cookie!");
					model.addAttribute("realName", "");
					model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
					List<NavListBean> navList = NavListBll.selectNavListIsShow();
					model.addAttribute("navList", navList);
					List<BookInfoBean> bookInfoList = null;
					if(bookType.equals("所有分类")){
						bookInfoList = bookInfoBll.selectInStoreBook();
						model.addAttribute("bookInfoList", bookInfoList);
					}else if(bookType.equals("新书上架")){
						bookInfoList = bookInfoBll.selectNewStoreBook();
						model.addAttribute("bookInfoList", bookInfoList);
					}else if(bookType.equals("畅销图书")){
						bookInfoList = bookInfoBll.selectHotStoreBook();
						model.addAttribute("bookInfoList", bookInfoList);
					}else if(bookType.equals("人气图书")){
						bookInfoList = bookInfoBll.selectHighStoreBook();
						model.addAttribute("bookInfoList", bookInfoList);
					}else{
						bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
						model.addAttribute("bookInfoList", bookInfoList);
					}
					model.addAttribute("bookType", bookType);
					List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
					model.addAttribute("bookCompareList", bookCompareList);
					List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
					model.addAttribute("shoppingCarList", shoppingCarList);
					return "user/catalog_list";
				}
			}
		}else{
			logger.info("Cookie中不存在有效信息！");
			model.addAttribute("realName", "");
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			List<NavListBean> navList = NavListBll.selectNavListIsShow();
			model.addAttribute("navList", navList);
			List<BookInfoBean> bookInfoList = null;
			if(bookType.equals("所有分类")){
				bookInfoList = bookInfoBll.selectInStoreBook();
				model.addAttribute("bookInfoList", bookInfoList);
			}else if(bookType.equals("新书上架")){
				bookInfoList = bookInfoBll.selectNewStoreBook();
				model.addAttribute("bookInfoList", bookInfoList);
			}else if(bookType.equals("畅销图书")){
				bookInfoList = bookInfoBll.selectHotStoreBook();
				model.addAttribute("bookInfoList", bookInfoList);
			}else if(bookType.equals("人气图书")){
				bookInfoList = bookInfoBll.selectHighStoreBook();
				model.addAttribute("bookInfoList", bookInfoList);
			}else{
				bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
				model.addAttribute("bookInfoList", bookInfoList);
			}
			model.addAttribute("bookType", bookType);
			List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
			model.addAttribute("bookCompareList", bookCompareList);
			List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
			model.addAttribute("shoppingCarList", shoppingCarList);
			return "user/catalog_list";
		}
		model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
		List<NavListBean> navList = NavListBll.selectNavListIsShow();
		model.addAttribute("navList", navList);
		List<BookInfoBean> bookInfoList = null;
		if(bookType.equals("所有分类")){
			bookInfoList = bookInfoBll.selectInStoreBook();
			model.addAttribute("bookInfoList", bookInfoList);
		}else if(bookType.equals("新书上架")){
			bookInfoList = bookInfoBll.selectNewStoreBook();
			model.addAttribute("bookInfoList", bookInfoList);
		}else if(bookType.equals("畅销图书")){
			bookInfoList = bookInfoBll.selectHotStoreBook();
			model.addAttribute("bookInfoList", bookInfoList);
		}else if(bookType.equals("人气图书")){
			bookInfoList = bookInfoBll.selectHighStoreBook();
			model.addAttribute("bookInfoList", bookInfoList);
		}else{
			bookInfoList =  bookInfoBll.selectInStoreBookByBookType(bookType);
			model.addAttribute("bookInfoList", bookInfoList);
		}
		model.addAttribute("bookType", bookType);
		List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
		model.addAttribute("bookCompareList", bookCompareList);
		List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
		model.addAttribute("shoppingCarList", shoppingCarList);
		return "user/catalog_list";
	}
	
	/**
	 *  书名模糊检索 Grid
	 * 
	 * @return
	 */
	@RequestMapping("bookInfoNameGrid")
	public String bookInfoNameGrid(String bookName,Model model,HttpServletRequest request) 
			throws IOException, TimeoutException, InterruptedException,
			MemcachedException{
		bookName = new String(bookName.getBytes("iso8859-1"),"utf-8");
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
						List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
						model.addAttribute("bookInfoList", bookInfoList);
						model.addAttribute("bookName", bookName);
						List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
						model.addAttribute("bookCompareList", bookCompareList);
						List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
						model.addAttribute("shoppingCarList", shoppingCarList);
						return "user/catalog_grid_search";
					} else {
						UserInfoBean bean = (UserInfoBean) landAndRegistrationBll.getMem(value);
						if(bean.getRealName() == null){
							model.addAttribute("realName", "");
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
							model.addAttribute("bookInfoList", bookInfoList);
							model.addAttribute("bookName", bookName);
							List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
							model.addAttribute("bookCompareList", bookCompareList);
							List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "user/catalog_grid_search";
						}else{
							model.addAttribute("realName", bean.getUserName());
							model.addAttribute("userCode", bean.getCode());
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
							model.addAttribute("bookInfoList", bookInfoList);
							model.addAttribute("bookName", bookName);
							List<BookCompareBean> bookCompareList = bookCompareBll.selectBookCompareInfoByUserCode(bean.getCode());
							model.addAttribute("bookCompareList", bookCompareList);
							List<ShoppingCarBean> shoppingCarList = shoppingCarBll.selectToCar(bean.getCode());
							for (ShoppingCarBean shoppingCarBean : shoppingCarList) {
								shoppingCarBean.setBookInfoBean(bookInfoBll.selectBookInfoByCode(shoppingCarBean.getBookCode()));
							}
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "user/catalog_grid_search";
						}
					}
				}else{
					logger.info("找不到名为accountuuid的Cookie!");
					model.addAttribute("realName", "");
					model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
					List<NavListBean> navList = NavListBll.selectNavListIsShow();
					model.addAttribute("navList", navList);
					List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
					model.addAttribute("bookInfoList", bookInfoList);
					model.addAttribute("bookName", bookName);
					List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
					model.addAttribute("bookCompareList", bookCompareList);
					List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
					model.addAttribute("shoppingCarList", shoppingCarList);
					return "user/catalog_grid_search";
				}
			}
		}else{
			logger.info("Cookie中不存在有效信息！");
			model.addAttribute("realName", "");
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			List<NavListBean> navList = NavListBll.selectNavListIsShow();
			model.addAttribute("navList", navList);
			List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
			model.addAttribute("bookInfoList", bookInfoList);
			model.addAttribute("bookName", bookName);
			List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
			model.addAttribute("bookCompareList", bookCompareList);
			List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
			model.addAttribute("shoppingCarList", shoppingCarList);
			return "user/catalog_grid_search";
		}
		model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
		List<NavListBean> navList = NavListBll.selectNavListIsShow();
		model.addAttribute("navList", navList);
		List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
		model.addAttribute("bookInfoList", bookInfoList);
		model.addAttribute("bookName", bookName);
		List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
		model.addAttribute("bookCompareList", bookCompareList);
		List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
		model.addAttribute("shoppingCarList", shoppingCarList);
		return "user/catalog_grid_search";
	}
	
	/**
	 * 书名模糊检索List
	 * 
	 * @return
	 */
	@RequestMapping("bookInfoNameList")
	public String bookInfoNameList(String bookName,Model model,HttpServletRequest request) 
			throws IOException, TimeoutException, InterruptedException,
			MemcachedException{
		bookName = new String(bookName.getBytes("iso8859-1"),"utf-8");
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
						List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
						model.addAttribute("bookInfoList", bookInfoList);
						model.addAttribute("bookName", bookName);
						List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
						model.addAttribute("bookCompareList", bookCompareList);
						List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
						model.addAttribute("shoppingCarList", shoppingCarList);
						return "user/catalog_list_search";
					} else {
						UserInfoBean bean = (UserInfoBean) landAndRegistrationBll.getMem(value);
						if(bean.getRealName() == null){
							model.addAttribute("realName", "");
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
							model.addAttribute("bookInfoList", bookInfoList);
							model.addAttribute("bookName", bookName);
							List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
							model.addAttribute("bookCompareList", bookCompareList);
							List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "user/catalog_list_search";
						}else{
							model.addAttribute("realName", bean.getUserName());
							model.addAttribute("userCode", bean.getCode());
							model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
							List<NavListBean> navList = NavListBll.selectNavListIsShow();
							model.addAttribute("navList", navList);
							List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
							model.addAttribute("bookInfoList", bookInfoList);
							model.addAttribute("bookName", bookName);
							List<BookCompareBean> bookCompareList = bookCompareBll.selectBookCompareInfoByUserCode(bean.getCode());
							model.addAttribute("bookCompareList", bookCompareList);
							List<ShoppingCarBean> shoppingCarList = shoppingCarBll.selectToCar(bean.getCode());
							for (ShoppingCarBean shoppingCarBean : shoppingCarList) {
								shoppingCarBean.setBookInfoBean(bookInfoBll.selectBookInfoByCode(shoppingCarBean.getBookCode()));
							}
							model.addAttribute("shoppingCarList", shoppingCarList);
							return "user/catalog_list_search";
						}
					}
				}else{
					logger.info("找不到名为accountuuid的Cookie!");
					model.addAttribute("realName", "");
					model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
					List<NavListBean> navList = NavListBll.selectNavListIsShow();
					model.addAttribute("navList", navList);
					List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
					model.addAttribute("bookInfoList", bookInfoList);
					model.addAttribute("bookName", bookName);
					List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
					model.addAttribute("bookCompareList", bookCompareList);
					List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
					model.addAttribute("shoppingCarList", shoppingCarList);
					return "user/catalog_list_search";
				}
			}
		}else{
			logger.info("Cookie中不存在有效信息！");
			model.addAttribute("realName", "");
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			List<NavListBean> navList = NavListBll.selectNavListIsShow();
			model.addAttribute("navList", navList);
			List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
			model.addAttribute("bookInfoList", bookInfoList);
			model.addAttribute("bookName", bookName);
			List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
			model.addAttribute("bookCompareList", bookCompareList);
			List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
			model.addAttribute("shoppingCarList", shoppingCarList);
			return "user/catalog_list_search";
		}
		model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
		List<NavListBean> navList = NavListBll.selectNavListIsShow();
		model.addAttribute("navList", navList);
		List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBookByBookName(bookName);
		model.addAttribute("bookInfoList", bookInfoList);
		model.addAttribute("bookName", bookName);
		List<BookCompareBean> bookCompareList = new ArrayList<BookCompareBean>();
		model.addAttribute("bookCompareList", bookCompareList);
		List<ShoppingCarBean> shoppingCarList = new ArrayList<ShoppingCarBean>();
		model.addAttribute("shoppingCarList", shoppingCarList);
		return "user/catalog_list_search";
	}

}
