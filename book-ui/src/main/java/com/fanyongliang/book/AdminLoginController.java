package com.fanyongliang.book;
/*------------------------------------------------------------------------- 
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 管理员登录的控制器
-------------------------------------------------------------------------*/

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.beans.UserPasswdBean;
import com.fanyongliang.book.bll.LandAndRegistrationBll;
/**
 * 管理员登录
 */
@Controller
@RequestMapping("admin")
public class AdminLoginController {
	/**
	 * 创建UserInfoBLL对象实例
	 */
	private LandAndRegistrationBll landAndRegistrationBll = new LandAndRegistrationBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AdminLoginController.class);

	/**
	 * 验证登录,添加Cookie
	 * 
	 * @param userLogin
	 * @param model
	 * @return
	 * @throws MemcachedException
	 * @throws InterruptedException
	 * @throws TimeoutException
	 * @throws IOException
	 */
	@RequestMapping("loginpass")
	public String loginpass(UserPasswdBean userPasswdBean, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, TimeoutException, InterruptedException,
			MemcachedException {
		String userName = new String(userPasswdBean.getUserName().getBytes("iso8859-1"),"utf-8");
		String userPasswd = new String(userPasswdBean.getUserPasswd().getBytes("iso8859-1"),"utf-8");
		UserInfoBean u = landAndRegistrationBll.getUserInfoBySelect(userName);
		if(u == null){
			logger.info("用户名或密码错误!");
			model.addAttribute("msg", "用户名或密码错误!");
			return "admin/adminlogin";
		}else{
			UserPasswdBean password = landAndRegistrationBll.getUserPasswordBySelect(userName);
			if(password.getUserType() == 2){
				logger.info("没有权限!");
				model.addAttribute("userName", userName);
				model.addAttribute("msg", "没有权限!");
				return "admin/adminlogin";
			}else{
				if (landAndRegistrationBll.checkLoginAccount(userName,
						userPasswd) == true) {
					HttpSession session = request.getSession();
					session.setAttribute("adminInfo", u);
					return "redirect:index";
				} else {
					logger.info("密码错误!");
					model.addAttribute("userName", userName);
					model.addAttribute("msg", "密码错误!");
					return "admin/adminlogin";
				}
			}
		}

	}

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("login")
	public String login() {
		return "admin/adminlogin";
	}
	
	/**
	 * 登出
	 * @param request
	 * @return
	 */
	@RequestMapping("loginout")
	public String loginout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("adminInfo");
		return "admin/adminlogin";
	}
	

}
