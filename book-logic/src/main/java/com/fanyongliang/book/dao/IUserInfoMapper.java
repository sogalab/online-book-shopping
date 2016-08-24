package com.fanyongliang.book.dao;

import java.util.List;

import com.fanyongliang.book.beans.BookInfoBean;
import com.fanyongliang.book.beans.UserInfoBean;

public interface IUserInfoMapper {

	/**
	 * 将传来的用户信息对象插入到数据库中
	 * @param userInfo
	 * @return
	 */
	public void insertUserInfo(UserInfoBean userInfoBean);
	/**
	 * 更新用户信息
	 * @param userInfoBean
	 */
	public void updateUserInfo(UserInfoBean userInfoBean);
	/**
	 * 删除用户
	 * @param userName
	 */
	public void deleteByUserName(String userName);
/*	*//**
	 * 根据用户名删除数据库中的记录
	 * @param info_account
	 * @return
	 *//*
	public Integer deleteByAccount(String info_account);*/
//	/**
//	 * 根据传入的对象内容更新数据库中的记录
//	 * @param userInfo
//	 * @return
//	 */
//	public Integer update(UserInfo userInfo);
	/**
	 * 根据用户名查找数据库中相应的记录
	 * @param info_account
	 * @return
	 */
	public UserInfoBean selectByUserName(String userName);
	/**
	 * 查询数据库中的所有记录，返回一个List集合
	 * @return
	 */
	public List<UserInfoBean> selectAllUsers();
//	/**
//	 * 根据关键字查询数据库中的所有记录，返回一个List集合
//	 * @param key
//	 * @return
//	 */
//	public List<UserInfo> selectByKey(String key);
//	/**
//	 * 根据分页查询所有用户信息，返回一个List集合
//	 * @param page
//	 * @return
//	 */
//	public List<UserInfo> selectByPage(int page);
//	/**
//	 * 查询数据库中所有记录的总数
//	 * @return
//	 */
//	public Integer selectCount();
}
