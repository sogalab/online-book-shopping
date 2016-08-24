package com.fanyongliang.book.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fanyongliang.book.beans.UserPasswdBean;

/**
 *用户密码数据处理类
 */
public class UserPasswdDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	public UserPasswdDAO() {
		sqlSessionFactory = MyBatisConnectionFactory
				.getSqlAccountSessionFactory();
	}
	/**
	 * 将用户信息录入到数据库中，用于记录录入的数量
	 * @param userInfo
	 * @return
	 */
	public void add(UserPasswdBean userPasswdBean) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserPasswdMapper dao = session.getMapper(IUserPasswdMapper.class);
			dao.insertUserPasswd(userPasswdBean);
		} finally {
			session.commit();
			session.close();
		}
	}
	
	/**
	 * 用户修改密码
	 * @param userPasswdBean
	 * @return
	 */
	public void update(UserPasswdBean userPasswdBean) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserPasswdMapper dao = session.getMapper(IUserPasswdMapper.class);
			dao.updateUserPasswd(userPasswdBean);
		} finally {
			session.commit();
			session.close();
		}
	}
	/**
	 * 通过用户名获取用户密码
	 * @param userName
	 * @return
	 */
	public UserPasswdBean getUserPasswordByUserName(String userName) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserPasswdMapper dao = session.getMapper(IUserPasswdMapper.class);
			return dao.getUserPasswordByUserName(userName);
		} finally {
			session.close();
		}
	}
	
}
