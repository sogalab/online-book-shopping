package com.fanyongliang.book.dao;

import com.fanyongliang.book.beans.UserPasswdBean;

public interface IUserPasswdMapper {
	/**
	 * 插入用户密码Bean
	 * @param userPasswdBean
	 * @return 
	 */
	public void insertUserPasswd(UserPasswdBean userPasswdBean);
	/**
	 * 修改用户密码
	 * @param userPasswdBean
	 * @return 
	 */
	public void updateUserPasswd(UserPasswdBean userPasswdBean);
	/**
	 * 查找用户密码靠用户名
	 * @param userName 用户名
	 * @return 用户密码
	 */
	public String selectPasswdByUserName(String userName);
	/**
	 * 
	 * @param userId
	 */
	public void updateUserName(Integer userId);
	
	public UserPasswdBean getUserPasswordByUserName(String userName);
}
