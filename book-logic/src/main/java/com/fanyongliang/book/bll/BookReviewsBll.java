package com.fanyongliang.book.bll;

import java.util.List;

import com.fanyongliang.book.beans.BookReviewsBean;
import com.fanyongliang.book.dao.BookReviewsDAO;


/**
 * 评论管理
 */
public class BookReviewsBll {
	BookReviewsDAO bookReviewsDAO = new BookReviewsDAO();
	
	/**
	 * 添加图书评论
	 * @param bookReviewsBean
	 */
	public void addBookReviews(BookReviewsBean bookReviewsBean) {
		bookReviewsDAO.addBookReviews(bookReviewsBean);
	}
	/**
	 * 根据图书编号查询已经通过的评论内容
	 * @param bookCode
	 */
	public List<BookReviewsBean> selectBookReviewsInfoByBookCode(Integer bookCode) {
		return bookReviewsDAO.selectBookReviewsInfoByBookCode(bookCode);
	}
	/**
	 * 查询所有未审核的评论
	 */
	public List<BookReviewsBean> selectAllNoAccessReviews() {
		return bookReviewsDAO.selectAllNoAccessReviews();
	}
	/**
	 * 查询所有通过的评论
	 */
	public List<BookReviewsBean> selectAllPassReviews() {
		return bookReviewsDAO.selectAllPassReviews();
	}
	/**
	 * 查询所有未通过的评论
	 */
	public List<BookReviewsBean> selectAllNoPassReviews() {
		return bookReviewsDAO.selectAllNoPassReviews();
	}
	/**
	 * 修改状态为通过
	 * @param code
	 */
	public void updateReviewsPass(Integer code) {
		bookReviewsDAO.updateReviewsPass(code);
	}
	/**
	 * 修改状态为未通过
	 * @param code
	 */
	public void updateReviewsNoPass(Integer code) {
		bookReviewsDAO.updateReviewsNoPass(code);
	}
}
