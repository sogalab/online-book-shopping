package com.fanyongliang.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fanyongliang.book.beans.BookReviewsBean;

/*
 * 书评信息数据处理类
 */
public class BookReviewsDAO {
	private SqlSessionFactory sqlSessionFactory;
	private IBookReviewsMapper iBookReviewsMapper ;

	public BookReviewsDAO() {
		sqlSessionFactory = MyBatisConnectionFactory
				.getSqlAccountSessionFactory();
	}
	
	/**
	 * 添加图书评论
	 * @param bookReviewsBean
	 */
	public void addBookReviews(BookReviewsBean bookReviewsBean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iBookReviewsMapper = session.getMapper(IBookReviewsMapper.class);
			iBookReviewsMapper.addBookReviews(bookReviewsBean);
		} finally {
			session.commit();
			session.close();
		}
	}
	/**
	 * 根据图书编号查询已经通过的评论内容
	 * @param bookCode
	 */
	public List<BookReviewsBean> selectBookReviewsInfoByBookCode(Integer bookCode){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iBookReviewsMapper = session.getMapper(IBookReviewsMapper.class);
			return iBookReviewsMapper.selectBookReviewsInfoByBookCode(bookCode);
		} finally {
			session.close();
		}
	}
	/**
	 * 查询所有未审核的评论
	 */
	public List<BookReviewsBean> selectAllNoAccessReviews(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iBookReviewsMapper = session.getMapper(IBookReviewsMapper.class);
			return iBookReviewsMapper.selectAllNoAccessReviews();
		} finally {
			session.close();
		}
	}
	/**
	 * 查询所有通过的评论
	 */
	public List<BookReviewsBean> selectAllPassReviews(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iBookReviewsMapper = session.getMapper(IBookReviewsMapper.class);
			return iBookReviewsMapper.selectAllPassReviews();
		} finally {
			session.close();
		}
	}
	/**
	 * 查询所有未通过的评论
	 */
	public List<BookReviewsBean> selectAllNoPassReviews(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iBookReviewsMapper = session.getMapper(IBookReviewsMapper.class);
			return iBookReviewsMapper.selectAllNoPassReviews();
		} finally {
			session.close();
		}
	}
	/**
	 * 修改状态为通过
	 * @param code
	 */
	public void updateReviewsPass(Integer code){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iBookReviewsMapper = session.getMapper(IBookReviewsMapper.class);
			iBookReviewsMapper.updateReviewsPass(code);
		} finally {
			session.commit();
			session.close();
		}
	}
	/**
	 * 修改状态为未通过
	 * @param code
	 */
	public void updateReviewsNoPass(Integer code){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iBookReviewsMapper = session.getMapper(IBookReviewsMapper.class);
			iBookReviewsMapper.updateReviewsNoPass(code);
		} finally {
			session.commit();
			session.close();
		}
	}
}
