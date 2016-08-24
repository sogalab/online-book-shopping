package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 管理用户修改密码的控制器
 -------------------------------------------------------------------------*/


import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.LandAndRegistrationBll;


@Controller
@RequestMapping("/user")
public class UserForgetController {

	private LandAndRegistrationBll landAndRegistrationBll = new LandAndRegistrationBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserForgetController.class);

	/**
	 * 忘记密码第一步
	 * 
	 * @return
	 */
	@RequestMapping("forgetshow")
	public String show(Model model){
		
		
		return "user/forget1";
	}
	/**
	 * 验证身份证号
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("forgetvalidate")
	public String forgetvalidate(UserInfoBean userInfoBean,Model model) throws UnsupportedEncodingException{
		String userName =  new String(userInfoBean.getUserName().getBytes("iso8859-1"),"utf-8");
		String idCard =  new String(userInfoBean.getIdCard().getBytes("iso8859-1"),"utf-8");
		UserInfoBean u = landAndRegistrationBll.getUserInfoBySelect(userName);
		if(u == null){
			model.addAttribute("msg", "帐号不存在！");
			return "user/forget1";
		}else{
			if(idCard.equals(u.getIdCard())){
				model.addAttribute("userName", userName);
				return "user/forget2";
			}else{
				model.addAttribute("idCard", idCard);
				model.addAttribute("userName", userName);
				model.addAttribute("msg", "绑定身份证号不正确！");
				return "user/forget1";
			}
		}
	}
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping("update")
	public String update(UserInfoBean userInfoBean,Model model){
		if(userInfoBean.getUserPasswd() == "" || userInfoBean.getUserPasswdRP() == ""){
			model.addAttribute("userName", userInfoBean.getUserName());
			model.addAttribute("msg", "带*号的均不能为空！");
			return "user/forget2";
		}
		if(!userInfoBean.getUserPasswd().equals(userInfoBean.getUserPasswdRP())){
			model.addAttribute("userName", userInfoBean.getUserName());
			model.addAttribute("msg", "两次输入密码不一致！");
			return "user/forget2";
		}
		landAndRegistrationBll.updateUserPassword(userInfoBean);
		logger.info("修改密码成功! ...");
		return "redirect:/user/login";
	}

}
