package com.fanyongliang.book.dao;

import java.util.List;

import com.fanyongliang.book.beans.BookReviewsBean;

public interface IBookReviewsMapper {
	/**
	 * 添加图书评论
	 * @param bookReviewsBean
	 */
	public void addBookReviews(BookReviewsBean bookReviewsBean);
	/**
	 * 根据图书编号查询已经通过的评论内容
	 * @param bookCode
	 */
	public List<BookReviewsBean> selectBookReviewsInfoByBookCode(Integer bookCode);
	/**
	 * 查询所有未审核的评论
	 */
	public List<BookReviewsBean> selectAllNoAccessReviews();
	/**
	 * 查询所有通过的评论
	 */
	public List<BookReviewsBean> selectAllPassReviews();
	/**
	 * 查询所有未通过的评论
	 */
	public List<BookReviewsBean> selectAllNoPassReviews();
	/**
	 * 修改状态为通过
	 * @param code
	 */
	public void updateReviewsPass(Integer code);
	/**
	 * 修改状态为未通过
	 * @param code
	 */
	public void updateReviewsNoPass(Integer code);
}
