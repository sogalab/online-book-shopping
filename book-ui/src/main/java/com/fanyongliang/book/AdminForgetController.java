package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 管理员修改密码的控制器
 -------------------------------------------------------------------------*/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.LandAndRegistrationBll;


@Controller
@RequestMapping("/admin")
public class AdminForgetController {

	private LandAndRegistrationBll landAndRegistrationBll = new LandAndRegistrationBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AdminForgetController.class);
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(UserInfoBean userInfoBean,Model model){
		if(userInfoBean.getUserPasswd() == "" || userInfoBean.getUserPasswdRP() == ""){
			model.addAttribute("userName", userInfoBean.getUserName());
			return "带*号的均不能为空！";
		}
		if(!userInfoBean.getUserPasswd().equals(userInfoBean.getUserPasswdRP())){
			model.addAttribute("userName", userInfoBean.getUserName());
			return "两次输入密码不一致！";
		}
		landAndRegistrationBll.updateUserPassword(userInfoBean);
		return "修改密码成功! ...";
	}
	@RequestMapping("updatePassword")
	public String updatePassword(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			return "admin/adminforget";
		}
	}

}
