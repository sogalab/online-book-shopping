package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 管理员导航控制器
 -------------------------------------------------------------------------*/

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fanyongliang.book.beans.NavListBean;
import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.NavListBll;


@Controller
@RequestMapping("/admin")
public class AdminNavController {
	private NavListBll navListBll = new NavListBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AdminNavController.class);

	/**
	 * 导航菜单页
	 * 
	 * @return
	 */
	@RequestMapping("navigation")
	public String navigation(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<NavListBean> navList = navListBll.selectAllNavList();
			model.addAttribute("navList", navList);
			return "admin/navigation";
		}
	}
	/**
	 * 增加一个新导航
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("addnavigation")
	public String addnavigation(String navPareName,Integer isShow ,Model model) throws UnsupportedEncodingException {
		navPareName = new String(navPareName.getBytes("iso8859-1"),"utf-8");
		NavListBean navListBean = new NavListBean();
		navListBean.setNavPareName(navPareName);
		navListBean.setIsShow(isShow);
		navListBll.addNewNav(navListBean);
		return "redirect:navigation";
	}
	/**
	 * 修改导航
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("updatenavigation")
	public String updatenavigation(Integer code,String navPareName,Integer isShow ,Model model) throws UnsupportedEncodingException {
		navPareName = new String(navPareName.getBytes("iso8859-1"),"utf-8");
		NavListBean navListBean = new NavListBean();
		navListBean.setCode(code);
		navListBean.setNavPareName(navPareName);
		navListBean.setIsShow(isShow);
		navListBll.updateNavigation(navListBean);
		return "redirect:navigation";
	}

}
