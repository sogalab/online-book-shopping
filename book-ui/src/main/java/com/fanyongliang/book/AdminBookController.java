package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 作者：
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 图书管理
 -------------------------------------------------------------------------*/


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fanyongliang.book.beans.BookInfoBean;
import com.fanyongliang.book.beans.NavListBean;
import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.BookInfoBll;
import com.fanyongliang.book.bll.NavListBll;
import com.fanyongliang.book.dao.prop.SimpleProperties;



@Controller
@RequestMapping("/admin")
public class AdminBookController {
	private NavListBll navListBll = new NavListBll();
	private BookInfoBll bookInfoBll = new BookInfoBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AdminBookController.class);

	/**
	 *  未上架图书
	 * 
	 * @return
	 */
	@RequestMapping("outstorebook")
	public String outstorebook(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<NavListBean> navList = navListBll.selectAllNavList();
			model.addAttribute("navList", navList);
			List<BookInfoBean> bookInfoList = bookInfoBll.selectOutStoreBook();
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			model.addAttribute("bookInfoList", bookInfoList);
			return "admin/bookoutstore";
		}
	}
	/**
	 * 修改未上架图书
	 * @param bookInfoBean
	 * @param model
	 * @param file
	 * @param req
	 * @return
	 */
	@RequestMapping("outstorebookupdate")
	public String outstorebookupdate(BookInfoBean bookInfoBean,Model model,
			MultipartFile file, HttpServletRequest req) {
		/**
		 * 初始化bookInfoBean      0：否      1：是
		 */
		bookInfoBean.setBookDiscounts(0);
		bookInfoBean.setInputTime(new Date());
		bookInfoBean.setIsDiscounts(0);
		bookInfoBean.setIsHigh(0);
		bookInfoBean.setIsHot(0);
		bookInfoBean.setIsInstore(0);
		bookInfoBean.setIsNew(0);
		//end
		MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
		Date currentTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String date = sdf.format(currentTime);
		String realPath = SimpleProperties.getInstance().getStringProperty(
				"output-path");
		String uuid = UUID.randomUUID().toString();
		String s="";
		if(file.getOriginalFilename()!=null && !"".equals(file.getOriginalFilename())){
			s= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		
			
			if (file.isEmpty()) {
				logger.info("File not upload");
			} else {
				logger.info("========================================");
				logger.info("The length of the file: " + file.getSize());
				logger.info("The file name: " + date + "-" + uuid + s);
				logger.info(request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + request.getContextPath());
				logger.info("========================================");
				try {				
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, date + "-" + uuid + s));
				} catch (IOException e) {
					logger.error(e.toString());
				}
			}
			//date + "-" + uuid + s
			//图片名字
			bookInfoBean.setBookPicture(date + "-" + uuid + s);
		}
		bookInfoBll.updateOutStoreBook(bookInfoBean);
		return "redirect:outstorebook";
	}
	
	/**
	 * 添加未上架图书
	 * @param bookInfoBean
	 * @param model
	 * @param file
	 * @param req
	 * @return
	 */
	@RequestMapping("outstorebookadd")
	public String outstorebookadd(BookInfoBean bookInfoBean,Model model,
			MultipartFile file, HttpServletRequest req) {
		/**
		 * 初始化bookInfoBean      0：否      1：是
		 */
		bookInfoBean.setBookDiscounts(0);
		bookInfoBean.setInputTime(new Date());
		bookInfoBean.setIsDiscounts(0);
		bookInfoBean.setIsHigh(0);
		bookInfoBean.setIsHot(0);
		bookInfoBean.setIsInstore(0);
		bookInfoBean.setIsNew(0);
		//end
		MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
		Date currentTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String date = sdf.format(currentTime);
		String realPath = SimpleProperties.getInstance().getStringProperty(
				"output-path");
		String uuid = UUID.randomUUID().toString();
		String s="";
		if(file.getOriginalFilename()!=null && !"".equals(file.getOriginalFilename())){
			s= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
			if (file.isEmpty()) {
				logger.info("File not upload");
			} else {
				logger.info("========================================");
				logger.info("The length of the file: " + file.getSize());
				logger.info("The file name: " + date + "-" + uuid + s);
				logger.info(request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + request.getContextPath());
				logger.info("========================================");
				try {				
					FileUtils.copyInputStreamToFile(file.getInputStream(), 
							new File(realPath, date + "-" + uuid + s));
				} catch (IOException e) {
					logger.error(e.toString());
				}
			}
			//date + "-" + uuid + s
			//图片名字
			bookInfoBean.setBookPicture(date + "-" + uuid + s);
		}
		bookInfoBll.addOutStoreBook(bookInfoBean);
		return "redirect:outstorebook";
	}
	/**
	 * 删除未上架图书
	 * @return
	 */
	@RequestMapping("outstorebookdelete")
	public String outstorebookdelete(Integer code) {
		bookInfoBll.deleteOutStoreBook(code);
		return "redirect:outstorebook";
	}
	/**
	 * 未上架 ——>上架
	 * @return
	 */
	@RequestMapping("outstorebookin")
	public String outstorebookin(Integer code) {
		bookInfoBll.inOutStoreBook(code);
		return "redirect:outstorebook";
	}
	/**
	 *  已上架图书
	 * 
	 * @return
	 */
	@RequestMapping("instorebook")
	public String instorebook(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<NavListBean> navList = navListBll.selectAllNavList();
			model.addAttribute("navList", navList);
			List<BookInfoBean> bookInfoList = bookInfoBll.selectInStoreBook();
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			model.addAttribute("bookInfoList", bookInfoList);
			return "admin/bookinstore";
		}
	}
	
	/**
	 * 修改已上架图书
	 * @param bookInfoBean
	 * @param model
	 * @param file
	 * @param req
	 * @return
	 */
	@RequestMapping("instorebookupdate")
	public String instorebookupdate(BookInfoBean bookInfoBean,Model model,
			MultipartFile file, HttpServletRequest req) {
		/**
		 * 初始化bookInfoBean      0：否      1：是
		 */
		bookInfoBean.setBookDiscounts(0);
		bookInfoBean.setInputTime(new Date());
		bookInfoBean.setIsDiscounts(0);
		bookInfoBean.setIsHigh(0);
		bookInfoBean.setIsHot(0);
		bookInfoBean.setIsInstore(0);
		bookInfoBean.setIsNew(0);
		//end
		MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
		Date currentTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String date = sdf.format(currentTime);
		String realPath = SimpleProperties.getInstance().getStringProperty(
				"output-path");
		String uuid = UUID.randomUUID().toString();
		String s="";
		if(file.getOriginalFilename()!=null && !"".equals(file.getOriginalFilename())){
			s= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		
			
			if (file.isEmpty()) {
				logger.info("File not upload");
			} else {
				logger.info("========================================");
				logger.info("The length of the file: " + file.getSize());
				logger.info("The file name: " + date + "-" + uuid + s);
				logger.info(request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + request.getContextPath());
				logger.info("========================================");
				try {				
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, date + "-" + uuid + s));
				} catch (IOException e) {
					logger.error(e.toString());
				}
			}
			//date + "-" + uuid + s
			//图片名字
			bookInfoBean.setBookPicture(date + "-" + uuid + s);
		}
		bookInfoBll.updateOutStoreBook(bookInfoBean);
		return "redirect:instorebook";
	}
	/**
	 * 删除未上架图书
	 * @return
	 */
	@RequestMapping("instorebookdelete")
	public String instorebookdelete(Integer code) {
		bookInfoBll.deleteOutStoreBook(code);
		return "redirect:instorebook";
	}
	
	/**
	 * 添加折扣
	 * @return
	 */
	@RequestMapping("instorebookdisc")
	public String instorebookdisc(Integer code,double bookDiscounts) {
		BookInfoBean bookInfoBean = new BookInfoBean();
		bookInfoBean.setCode(code);
		bookInfoBean.setBookDiscounts(bookDiscounts);
		bookInfoBean.setIsDiscounts(1);
		bookInfoBll.instorebookdisc(bookInfoBean);
		return "redirect:instorebook";
	}
	/**
	 * 新书推荐
	 * @return
	 */
	@RequestMapping("instorebooknew")
	public String instorebooknew(Integer code) {
		bookInfoBll.instorebooknew(code);
		return "redirect:instorebook";
	}
	/**
	 * 热门推荐
	 * @return
	 */
	@RequestMapping("instorebookhot")
	public String instorebookhot(Integer code) {
		bookInfoBll.instorebookhot(code);
		return "redirect:instorebook";
	}
	/**
	 * 评分推荐
	 * @return
	 */
	@RequestMapping("instorebookhigh")
	public String instorebookhigh(Integer code) {
		bookInfoBll.instorebookhigh(code);
		return "redirect:instorebook";
	}
	/**
	 * 取消折扣
	 */
	@RequestMapping("deleteinstorebookdisc")
	public String deleteinstorebookdisc(Integer code) {
		bookInfoBll.deleteinstorebookdisc(code);
		return "redirect:instorebook";
	}
	/**
	 * 取消新书推荐
	 */
	@RequestMapping("deleteinstorebooknew")
	public String deleteinstorebooknew(Integer code) {
		bookInfoBll.deleteinstorebooknew(code);
		return "redirect:instorebook";
	}
	/**
	 * 取消热门推荐
	 */
	@RequestMapping("deleteinstorebookhot")
	public String deleteinstorebookhot(Integer code) {
		bookInfoBll.deleteinstorebookhot(code);
		return "redirect:instorebook";
	}
	/**
	 * 取消评分推荐
	 */
	@RequestMapping("deleteinstorebookhigh")
	public String deleteinstorebookhigh(Integer code) {
		bookInfoBll.deleteinstorebookhigh(code);
		return "redirect:instorebook";
	}
	/**
	 *  有折扣图书
	 * 
	 * @return
	 */
	@RequestMapping("discstorebook")
	public String discstorebook(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<NavListBean> navList = navListBll.selectAllNavList();
			model.addAttribute("navList", navList);
			List<BookInfoBean> bookInfoList = bookInfoBll.selectDiscStoreBook();
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			model.addAttribute("bookInfoList", bookInfoList);
			return "admin/bookinstoredisc";
		}
	}
	/**
	 * 修改折扣
	 * @return
	 */
	@RequestMapping("instorebookdiscupdate")
	public String instorebookdiscupdate(Integer code,double bookDiscounts) {
		BookInfoBean bookInfoBean = new BookInfoBean();
		bookInfoBean.setCode(code);
		bookInfoBean.setBookDiscounts(bookDiscounts);
		bookInfoBll.instorebookdiscupdate(bookInfoBean);
		return "redirect:discstorebook";
	}
	/**
	 *  新书推荐图书
	 * 
	 * @return
	 */
	@RequestMapping("newstorebook")
	public String newstorebook(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<NavListBean> navList = navListBll.selectAllNavList();
			model.addAttribute("navList", navList);
			List<BookInfoBean> bookInfoList = bookInfoBll.selectNewStoreBook();
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			model.addAttribute("bookInfoList", bookInfoList);
			return "admin/bookinstorenew";
		}
	}
	/**
	 *  热门推荐图书
	 * 
	 * @return
	 */
	@RequestMapping("hotstorebook")
	public String hotstorebook(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<NavListBean> navList = navListBll.selectAllNavList();
			model.addAttribute("navList", navList);
			List<BookInfoBean> bookInfoList = bookInfoBll.selectHotStoreBook();
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			model.addAttribute("bookInfoList", bookInfoList);
			return "admin/bookinstorehot";
		}
	}
	/**
	 *  评分推荐图书
	 * 
	 * @return
	 */
	@RequestMapping("highstorebook")
	public String highstorebook(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<NavListBean> navList = navListBll.selectAllNavList();
			model.addAttribute("navList", navList);
			List<BookInfoBean> bookInfoList = bookInfoBll.selectHighStoreBook();
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			model.addAttribute("bookInfoList", bookInfoList);
			return "admin/bookinstorehigh";
		}
	}

}
