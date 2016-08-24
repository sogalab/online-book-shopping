package com.fanyongliang.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fanyongliang.book.beans.BookCompareBean;

/*
 * 图书比较信息管理
 */
public class BookCompareDAO {
	private SqlSessionFactory sqlSessionFactory;
	private IBookCompareMapper iBookCompareMapper;

	public BookCompareDAO() {
		sqlSessionFactory = MyBatisConnectionFactory
				.getSqlAccountSessionFactory();
	}

	/**
	 * 添加图书比较
	 * 
	 * @param bookCompareBean
	 */
	public void addBookCompareInfo(BookCompareBean bookCompareBean) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iBookCompareMapper = session.getMapper(IBookCompareMapper.class);
			iBookCompareMapper.addBookCompareInfo(bookCompareBean);
		} finally {
			session.commit();
			session.close();
		}
	}
	/**
	 * 查询某用户的图书比较信息
	 * 
	 * @param userCode
	 */
	public List<BookCompareBean> selectBookCompareInfoByUserCode(Integer userCode) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iBookCompareMapper = session.getMapper(IBookCompareMapper.class);
			return iBookCompareMapper.selectBookCompareInfoByUserCode(userCode);
		} finally {
			session.close();
		}
	}
	/**
	 * 删除某一条图书比较信息
	 */
	public void deleteBookCompareInfoByCode(Integer code) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iBookCompareMapper = session.getMapper(IBookCompareMapper.class);
			iBookCompareMapper.deleteBookCompareInfoByCode(code);
		} finally {
			session.commit();
			session.close();
		}
	}
	/**
	 * 清空某用户图书比较信息
	 */
	public void deleteBookCompareInfoAll(Integer userCode) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iBookCompareMapper = session.getMapper(IBookCompareMapper.class);
			iBookCompareMapper.deleteBookCompareInfoAll(userCode);
		} finally {
			session.commit();
			session.close();
		}
	}
}
