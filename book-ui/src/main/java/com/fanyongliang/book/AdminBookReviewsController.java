package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 作者：
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 评论管理
 -------------------------------------------------------------------------*/




import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fanyongliang.book.beans.BookReviewsBean;
import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.BookReviewsBll;



@Controller
@RequestMapping("/admin")
public class AdminBookReviewsController {
	private BookReviewsBll bookReviewsBll = new BookReviewsBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AdminBookReviewsController.class);

	/**
	 * 待审核评论
	 * @return
	 */
	@RequestMapping("/reviewswait")
	public String reviewsadd(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<BookReviewsBean> bookReviewsList = bookReviewsBll.selectAllNoAccessReviews();
			model.addAttribute("bookReviewsList", bookReviewsList);
			return "admin/reviewsall";
		}
	}
	/**
	 * 通过评论
	 * @return
	 */
	@RequestMapping("/reviewspass")
	public String reviewspass(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<BookReviewsBean> bookReviewsList = bookReviewsBll.selectAllPassReviews();
			model.addAttribute("bookReviewsList", bookReviewsList);
			return "admin/reviewspass";
		}
	}
	/**
	 * 未通过评论
	 * @return
	 */
	@RequestMapping("/reviewsnopass")
	public String reviewsnopass(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<BookReviewsBean> bookReviewsList = bookReviewsBll.selectAllNoPassReviews();
			model.addAttribute("bookReviewsList", bookReviewsList);
			return "admin/reviewsnopass";
		}
	}
	/**
	 * 审核通过
	 * @return
	 */
	@RequestMapping("/reviewstopass")
	public String reviewstopass(Integer code,Model model) {
		bookReviewsBll.updateReviewsPass(code);
		return "redirect:reviewswait";
	}
	/**
	 * 审核不通过
	 * @return
	 */
	@RequestMapping("/reviewstonopass")
	public String reviewstonopass(Integer code,Model model) {
		bookReviewsBll.updateReviewsNoPass(code);
		return "redirect:reviewswait";
	}
}
