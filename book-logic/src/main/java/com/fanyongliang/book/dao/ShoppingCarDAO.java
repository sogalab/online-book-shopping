package com.fanyongliang.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fanyongliang.book.beans.ShoppingCarBean;

/**
 * 购物车信息数据处理类
 */
public class ShoppingCarDAO {
	private SqlSessionFactory sqlSessionFactory;
	private IShoppingCarMapper iShoppingCarMapper ;

	public ShoppingCarDAO() {
		sqlSessionFactory = MyBatisConnectionFactory
				.getSqlAccountSessionFactory();
	}
	/**
	 * 购物车表增加数据
	 * @param schoppingCarBean
	 */
	public void insertToCar(ShoppingCarBean schoppingCarBean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iShoppingCarMapper = session.getMapper(IShoppingCarMapper.class);
			iShoppingCarMapper.insertToCar(schoppingCarBean);
			session.commit();
		} finally {
			session.close();
		}
	}
	/**
	 * 购物车表删除一条数据
	 * @param carCode
	 */
	public void deleteToCar(Integer carCode){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iShoppingCarMapper = session.getMapper(IShoppingCarMapper.class);
			iShoppingCarMapper.deleteToCar(carCode);
			session.commit();
		} finally {
			session.close();
		}
	}
	/**
	 * 购物车表全部删除
	 * @param userCode
	 */
	public void deleteAllToCar(Integer userCode){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iShoppingCarMapper = session.getMapper(IShoppingCarMapper.class);
			iShoppingCarMapper.deleteAllToCar(userCode);
			session.commit();
		} finally {
			session.close();
		}
	}
	/**
	 * 购物车表查询数据
	 * @param userCode
	 */
	public List<ShoppingCarBean> selectToCar(Integer userCode){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iShoppingCarMapper = session.getMapper(IShoppingCarMapper.class);
			return iShoppingCarMapper.selectToCar(userCode);
		} finally {
			session.close();
		}
	}
	/**
	 * 查询购物城中的数据根据书本Code
	 * @param schoppingCarBean
	 * @return ShoppingCarBean
	 */
	public ShoppingCarBean selectCarByBookCode(ShoppingCarBean schoppingCarBean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iShoppingCarMapper = session.getMapper(IShoppingCarMapper.class);
			return iShoppingCarMapper.selectCarByBookCode(schoppingCarBean);
		} finally {
			session.close();
		}
	}
	/**
	 * 更新表中的某一个数据 
	 * @param schoppingCarBean
	 */
	public void updateCarByCode(ShoppingCarBean schoppingCarBean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iShoppingCarMapper = session.getMapper(IShoppingCarMapper.class);
			iShoppingCarMapper.updateCarByCode(schoppingCarBean);
			session.commit();
		} finally {
			session.close();
		}
	}
}
