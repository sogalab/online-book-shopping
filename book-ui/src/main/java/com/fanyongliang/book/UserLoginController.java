package com.fanyongliang.book;

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
 * 管理用户登录控制器
 * @author fanyongliang
 */
@Controller
@RequestMapping("user")
public class UserLoginController {
	/**
	 * 创建UserInfoBLL对象实例
	 */
	private LandAndRegistrationBll landAndRegistrationBll = 
			new LandAndRegistrationBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserLoginController.class);

	/**
	 * 验证用户登录
	 * 
	 */
	@RequestMapping("loginpass")
	public String loginpass(UserPasswdBean userPasswdBean, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, TimeoutException, InterruptedException,
			MemcachedException {
		String userName = new String(userPasswdBean.getUserName().getBytes("iso8859-1"),"utf-8");
		String userPasswd = new String(userPasswdBean.getUserPasswd().getBytes("iso8859-1"),"utf-8");
		String validateCode = new String(userPasswdBean.getValidateCode().getBytes("iso8859-1"),"utf-8");
		validateCode = validateCode.toLowerCase();
		UserInfoBean u = landAndRegistrationBll.getUserInfoBySelect(userName);
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("validatecode");
		if(code != null){
			code = code.toLowerCase();
		}
        if(!validateCode.equals(code)){
        	logger.info("user:"+validateCode+"---sys:"+code);
			model.addAttribute("msg", "验证码输入错误!");
			return "user/login";
        }
		
		if(u == null){
			logger.info("用户名或密码错误!");
			model.addAttribute("msg", "用户名或密码错误!");
			return "user/login";
		}else{
			if (landAndRegistrationBll.checkLoginAccount(userName,
					userPasswd) == true) {
				// cookie
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				Cookie cUUID = new Cookie("accountuuid", uuid);
				cUUID.setMaxAge(3600);
				response.addCookie(cUUID);
				// memcache
				landAndRegistrationBll.setMem(uuid, u);
				return "redirect:index";
			} else {
				logger.info("用户名或密码错误!");
				model.addAttribute("msg", "用户名或密码错误!");
				return "user/login";
			}
		}

	}

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("login")
	public String show() {
		return "user/login";
	}

}
