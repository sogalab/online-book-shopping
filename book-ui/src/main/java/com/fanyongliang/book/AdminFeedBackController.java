package com.fanyongliang.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fanyongliang.book.beans.UserFeedbackBean;
import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.BookCompareBll;
import com.fanyongliang.book.bll.BookInfoBll;
import com.fanyongliang.book.bll.BookOrderBll;
import com.fanyongliang.book.bll.BookReviewsBll;
import com.fanyongliang.book.bll.LandAndRegistrationBll;
import com.fanyongliang.book.bll.NavListBll;
import com.fanyongliang.book.bll.ShoppingCarBll;
import com.fanyongliang.book.bll.UserFeedbackBll;

/**
 * 管理员反馈信息处理控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminFeedBackController {
	private NavListBll NavListBll = new NavListBll();
	private BookInfoBll bookInfoBll = new BookInfoBll();
	private BookReviewsBll bookReviewsBll = new BookReviewsBll();
	private LandAndRegistrationBll landAndRegistrationBll = new LandAndRegistrationBll();
	private BookCompareBll bookCompareBll = new BookCompareBll();
	private ShoppingCarBll shoppingCarBll = new ShoppingCarBll();
	private BookOrderBll bookOrderBll = new BookOrderBll();
	private UserFeedbackBll userFeedbackBll = new UserFeedbackBll();
	
	/**
	 * 查看反馈信息
	 * @param 
	 */
	@RequestMapping("feedbackshow")
	public String feedbackshow(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<UserFeedbackBean> userFeedbackList = userFeedbackBll.selectFeedback();
			model.addAttribute("userFeedbackList", userFeedbackList);
			return "admin/userfeedback";
	
		}
	}
}
