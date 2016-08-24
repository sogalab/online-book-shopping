package com.fanyongliang.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fanyongliang.book.beans.AdvPhotoBean;

/**
 * 网站广告信息数据访问类
 * 
 * @author fanyongliang
 *
 */
public class AdvPhotoDAO {
	private SqlSessionFactory sqlSessionFactory;
	private IAdvPhotoMapper iAdvPhotoMapper;

	public AdvPhotoDAO() {
		sqlSessionFactory = MyBatisConnectionFactory
				.getSqlAccountSessionFactory();
	}

	/**
	 * 查询所有滚动广告
	 * 
	 * @param
	 * @return
	 */
	public List<AdvPhotoBean> selectAllAdvPhoto(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iAdvPhotoMapper = session.getMapper(IAdvPhotoMapper.class);
			return iAdvPhotoMapper.selectAllAdvPhoto();
		} finally {
			session.close();
		}
	}
	/**
	 * 修改滚动广告
	 * 
	 * @param
	 * @return
	 */
	public void updateAdvPhoto(AdvPhotoBean advPhotoBean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iAdvPhotoMapper = session.getMapper(IAdvPhotoMapper.class);
			iAdvPhotoMapper.updateAdvPhoto(advPhotoBean);
		} finally {
			session.commit();
			session.close();
		}
	}
	
	/**
	 * 新增滚动广告
	 * 
	 * @param
	 * @return
	 */
	public void addAdvPhoto(AdvPhotoBean advPhotoBean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iAdvPhotoMapper = session.getMapper(IAdvPhotoMapper.class);
			iAdvPhotoMapper.addAdvPhoto(advPhotoBean);
		} finally {
			session.commit();
			session.close();
		}
	}
	/**
	 * 删除滚动广告
	 * 
	 * @param
	 * @return
	 */
	public void deleteAdvPhoto(Integer code){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			iAdvPhotoMapper = session.getMapper(IAdvPhotoMapper.class);
			iAdvPhotoMapper.deleteAdvPhoto(code);
		} finally {
			session.commit();
			session.close();
		}
	}
}
