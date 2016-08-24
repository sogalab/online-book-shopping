package com.fanyongliang.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.UserInfoBll;

/**
 * 管理员用户个人信息管理
 */
@Controller
@RequestMapping("admin")
public class AdminUserInfoController {
	private UserInfoBll userInfoBll = new UserInfoBll();
	/**
	 * 删除个人信息
	 */
	@RequestMapping("deleteUser")
	@ResponseBody
	public void deleteUser(String userName) {
		userInfoBll.deleteUser(userName);
	}
	/**
	 * 查询所有用户信息
	 */
	@RequestMapping("getAllUser")
	public String getAllUser(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<UserInfoBean> userInfoList = userInfoBll.selectAllUser();
			model.addAttribute("userInfoList", userInfoList);
			return "admin/adminuserlist";
		}
	}
	/**
	 * 查询某个用户信息
	 */
	public void getUserInfo(String userName,Model model){
		UserInfoBean userInfoBean = userInfoBll.selectUserByName(userName);
		model.addAttribute("userInfoBean", userInfoBean);
	}
}
