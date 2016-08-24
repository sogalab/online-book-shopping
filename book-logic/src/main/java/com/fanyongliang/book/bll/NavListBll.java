package com.fanyongliang.book.bll;

import java.util.List;

import com.fanyongliang.book.beans.NavListBean;
import com.fanyongliang.book.dao.NavListDAO;

/**
 * 导航信息数据实现类
 */
public class NavListBll {
	NavListDAO navListDAO = new NavListDAO();
	/**
	 * 查询所有显示的导航信息
	 * 
	 * @param
	 * @return
	 */
	public List<NavListBean> selectNavListIsShow() {
		return navListDAO.selectNavListIsShow();
	}
	/**
	 * 查询所有导航信息
	 * 
	 * @param
	 * @return
	 */
	public List<NavListBean> selectAllNavList() {
		return navListDAO.selectAllNavList();
	}
	/**
	 * 增加新的导航
	 * 
	 * @param
	 * @return
	 */
	public void addNewNav(NavListBean navListBean) {
		navListDAO.addNewNav(navListBean);
	}
	
	/**
	 * 修改导航
	 * 
	 * @param
	 * @return
	 */
	public void updateNavigation(NavListBean navListBean) {
		navListDAO.updateNavigation(navListBean);
	}
}
