package com.fanyongliang.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fanyongliang.book.beans.BookOrderInfoBean;

/**
 * 订单信息数据处理类
 */
public class BookOrderInfoDAO {
	private SqlSessionFactory sqlSessionFactory;
	private IBookOrderInfoMapper iBookOrderInfoMapper ;

	public BookOrderInfoDAO() {
		sqlSessionFactory = MyBatisConnectionFactory
				.getSqlAccountSessionFactory();
	}
	/**
	 * 删除订单详细信息
	 * @param orderCode
	 */
	public void deleteBookOrderInfo(Integer orderCode){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			iBookOrderInfoMapper = session.getMapper(IBookOrderInfoMapper.class);
			iBookOrderInfoMapper.deleteBookOrderInfo(orderCode);
			session.commit();
		}finally{
			session.close();
		}
	}
	/**
	 * 插入数据
	 * @param bookOrderInfoBean
	 */
	public void insertBookOrderInfo(BookOrderInfoBean bookOrderInfoBean){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			iBookOrderInfoMapper = session.getMapper(IBookOrderInfoMapper.class);
			iBookOrderInfoMapper.insertBookOrderInfo(bookOrderInfoBean);
			session.commit();
		}finally{
			session.close();
		}
	}

	/**
	 * 查找书本订单数据靠订单编号
	 * @param orderCode
	 * @return
	 */
	public List<BookOrderInfoBean> selectBookOrderInfoByOrderCode(Integer orderCode){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			iBookOrderInfoMapper = session.getMapper(IBookOrderInfoMapper.class);
			return iBookOrderInfoMapper.selectBookOrderInfoByOrderCode(orderCode);
		}finally{
			session.close();
		}
	}
}
