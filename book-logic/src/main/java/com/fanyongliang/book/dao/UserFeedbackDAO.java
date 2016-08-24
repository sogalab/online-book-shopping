package com.fanyongliang.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fanyongliang.book.beans.UserFeedbackBean;

public class UserFeedbackDAO {
	private SqlSessionFactory sqlSessionFactory;
	private IUserFeedbackMapper iUserFeedbackMapper;

	public UserFeedbackDAO() {
		sqlSessionFactory = MyBatisConnectionFactory
				.getSqlAccountSessionFactory();
	}

	/**
	 * 插入用户反馈
	 * @param userFeedbackBean
	 */
	public void insertFeedback(UserFeedbackBean userFeedbackBean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iUserFeedbackMapper = session.getMapper(IUserFeedbackMapper.class);
			iUserFeedbackMapper.insertFeedback(userFeedbackBean);
		} finally {
			session.commit();
			session.close();
		}
	}
	/**
	 * 查询搜有反馈
	 * @return
	 */
	public List<UserFeedbackBean> selectFeedback(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iUserFeedbackMapper = session.getMapper(IUserFeedbackMapper.class);
			return iUserFeedbackMapper.selectFeedback();
		} finally {
			session.close();
		}
	}
}
