package com.fanyongliang.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fanyongliang.book.beans.NavListBean;

/**
 * 导航信息数据处理类
 */
public class NavListDAO {
	private SqlSessionFactory sqlSessionFactory;
	private INavListMapper iNavListMapper;

	public NavListDAO() {
		sqlSessionFactory = MyBatisConnectionFactory
				.getSqlAccountSessionFactory();
	}

	/**
	 * 查询所有显示的导航信息
	 * 
	 * @param
	 * @return
	 */
	public List<NavListBean> selectNavListIsShow() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iNavListMapper = session.getMapper(INavListMapper.class);
			return iNavListMapper.selectNavListIsShow();
		} finally {
			session.close();
		}
	}
	/**
	 * 查询所有导航信息
	 * 
	 * @param
	 * @return
	 */
	public List<NavListBean> selectAllNavList(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iNavListMapper = session.getMapper(INavListMapper.class);
			return iNavListMapper.selectAllNavList();
		} finally {
			session.close();
		}
	}
	/**
	 * 增加新的导航
	 * 
	 * @param
	 * @return
	 */
	public void addNewNav(NavListBean navListBean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iNavListMapper = session.getMapper(INavListMapper.class);
			iNavListMapper.addNewNav(navListBean);
		} finally {
			session.commit();
			session.close();
		}
	}
	/**
	 * 修改导航
	 * 
	 * @param
	 * @return
	 */
	public void updateNavigation(NavListBean navListBean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iNavListMapper = session.getMapper(INavListMapper.class);
			iNavListMapper.updateNavigation(navListBean);
		} finally {
			session.commit();
			session.close();
		}
	}
}
