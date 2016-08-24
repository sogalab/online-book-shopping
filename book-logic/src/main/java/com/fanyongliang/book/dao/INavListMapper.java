package com.fanyongliang.book.dao;

import java.util.List;

import com.fanyongliang.book.beans.NavListBean;

public interface INavListMapper {
	/**
	 * 查询所有显示的导航信息
	 * 
	 * @param
	 * @return
	 */
	public List<NavListBean> selectNavListIsShow();
	/**
	 * 查询所有导航信息
	 * 
	 * @param
	 * @return
	 */
	public List<NavListBean> selectAllNavList();
	/**
	 * 增加新的导航
	 * 
	 * @param
	 * @return
	 */
	public void addNewNav(NavListBean navListBean);
	/**
	 * 修改导航
	 * 
	 * @param
	 * @return
	 */
	public void updateNavigation(NavListBean navListBean);
}
