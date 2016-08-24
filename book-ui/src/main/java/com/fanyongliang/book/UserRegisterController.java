package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 管理用户注册的控制器
-------------------------------------------------------------------------*/



import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.LandAndRegistrationBll;

/**
 * 管理用户注册
 *
 */
@Controller
@RequestMapping("/user")
public class UserRegisterController {
	/**
	 * 创建UserInfoBLL对象实例
	 */
	private LandAndRegistrationBll landAndRegistrationBll = new LandAndRegistrationBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserRegisterController.class);

	/**
	 * 用户注册验证，正确跳转到主页面
	 * 
	 * @param userInfo
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("registerpass")
	public String registerpass(UserInfoBean userInfoBean, Model model) throws UnsupportedEncodingException {
		String userName = userInfoBean.getUserName();
		String realName = userInfoBean.getRealName();
		userName =  new String(userName.getBytes("iso8859-1"),"utf-8");
		realName =  new String(realName.getBytes("iso8859-1"),"utf-8");
		String reg = "^\\d{15}|^\\d{17}([0-9]|X|x)$";
		
		if(userName == "" || realName == "" || userInfoBean.getUserPasswd() == "" || userInfoBean.getUserPasswdRP() == "" || userInfoBean.getIdCard() == "" || userInfoBean.getUserEmail() == ""){
			logger.info("带*号的不允许为空!");
			model.addAttribute("msg", "带*号的不允许为空!");
			model.addAttribute("userName", userName);
			model.addAttribute("realName", realName);
			model.addAttribute("idCard", userInfoBean.getIdCard());
			model.addAttribute("userEmail", userInfoBean.getUserEmail());
			return "user/regist";
		}else if(!userInfoBean.getUserPasswd().equals(userInfoBean.getUserPasswdRP())){
			logger.info("两次输入密码不一致!");
			model.addAttribute("msg", "两次输入密码不一致!");
			model.addAttribute("userName", userName);
			model.addAttribute("realName", realName);
			model.addAttribute("idCard", userInfoBean.getIdCard());
			model.addAttribute("userEmail", userInfoBean.getUserEmail());
			return "user/regist";
		}else if (!userInfoBean.getIdCard().matches(reg)) {
			logger.info("身份证格式不正确！!");
			model.addAttribute("msg", "身份证格式不正确！");
			model.addAttribute("userName", userName);
			model.addAttribute("realName", realName);
			model.addAttribute("idCard", userInfoBean.getIdCard());
			model.addAttribute("userEmail", userInfoBean.getUserEmail());
			return "user/regist";
		}else if(userInfoBean.getUserType()!= 2){
			logger.info("请勾选同意阅读条款!");
			model.addAttribute("msg", "请勾选同意阅读条款!");
			model.addAttribute("userName", userName);
			model.addAttribute("realName", realName);
			model.addAttribute("idCard", userInfoBean.getIdCard());
			model.addAttribute("userEmail", userInfoBean.getUserEmail());
			return "user/regist";
		}
		userInfoBean.setUserName(userName);
		userInfoBean.setRealName(realName);
		if (landAndRegistrationBll.checkRegisterUserName(userName) == true) {
			landAndRegistrationBll.addUserInfo(userInfoBean);
			logger.info("Welcome book store! ...");
			return "redirect:/user/login";
		} else {
			logger.info("用户名已存在!");
			model.addAttribute("msg", "用户名已存在,重新输入");
			model.addAttribute("realName", realName);
			model.addAttribute("idCard", userInfoBean.getIdCard());
			model.addAttribute("userEmail", userInfoBean.getUserEmail());
			return "user/regist";
		}
	}

	/**
	 * 跳转到注册页面
	 * 
	 * @return
	 */
	@RequestMapping("register")
	public String show() {
		return "user/regist";
	}

}
