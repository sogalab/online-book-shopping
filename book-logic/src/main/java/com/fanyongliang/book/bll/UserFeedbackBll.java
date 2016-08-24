package com.fanyongliang.book.bll;

import java.util.List;

import com.fanyongliang.book.beans.UserFeedbackBean;
import com.fanyongliang.book.dao.UserFeedbackDAO;

public class UserFeedbackBll {
	private UserFeedbackDAO userFeedbackDAO = new UserFeedbackDAO();;
	/**
	 * 增加用户反馈
	 * @param userFeedbackBean
	 */
	public void insertFeedback(UserFeedbackBean userFeedbackBean){
		userFeedbackDAO.insertFeedback(userFeedbackBean);
	}
	/**
	 * 查询所有反馈
	 * @return
	 */
	public List<UserFeedbackBean> selectFeedback(){
		return userFeedbackDAO.selectFeedback();
	}
}
