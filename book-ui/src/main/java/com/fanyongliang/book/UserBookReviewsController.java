package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 作者：
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 评论管理
 -------------------------------------------------------------------------*/



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanyongliang.book.beans.BookReviewsBean;
import com.fanyongliang.book.bll.BookReviewsBll;



@Controller
@RequestMapping("/user")
public class UserBookReviewsController {
	private BookReviewsBll bookReviewsBll = new BookReviewsBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserBookReviewsController.class);

	/**
	 * 添加图书评论
	 * @param bookReviewsBean
	 * @param model
	 * @return
	 */
	@RequestMapping("/reviewsadd")
	@ResponseBody
	public void reviewsadd(BookReviewsBean bookReviewsBean,Model model) {
		bookReviewsBean.setBookReviewsTime(new Date());
		bookReviewsBean.setIsDelete(0);
		bookReviewsBean.setIsAccess(0);
		bookReviewsBll.addBookReviews(bookReviewsBean);
	}
}
