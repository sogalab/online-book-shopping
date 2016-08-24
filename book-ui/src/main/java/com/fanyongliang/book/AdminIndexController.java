package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 作者：
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 管理员主页
 -------------------------------------------------------------------------*/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fanyongliang.book.beans.UserInfoBean;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AdminIndexController.class);

	/**
	 * 管理员主页
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			logger.info("欢迎进入管理员主页！");
			model.addAttribute("u", u);
			return "admin/welcome";
		}
	}

}
