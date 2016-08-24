package com.fanyongliang.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fanyongliang.book.beans.UserAddressBean;

/**
 *用户地址数据处理类
 */
public class UserAddressDAO {
	private SqlSessionFactory sqlSessionFactory;
	private IUserAddressMapper iUserAddressMapper ;

	public UserAddressDAO() {
		sqlSessionFactory = MyBatisConnectionFactory
				.getSqlAccountSessionFactory();
	}
	/**
	 * 增加用户收货地址
	 * @param userAddressBean
	 */
	public void insertAddress(UserAddressBean userAddressBean){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			iUserAddressMapper =session.getMapper(IUserAddressMapper.class);
			iUserAddressMapper.insertAddress(userAddressBean);
		}finally{
			session.commit();
			session.close();
		}
	}
	/**
	 * 更新用户收货地址
	 * @param userAddressBean
	 */
	public void updateAddress(UserAddressBean userAddressBean){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			iUserAddressMapper =session.getMapper(IUserAddressMapper.class);
			iUserAddressMapper.updateAddress(userAddressBean);
		}finally{
			session.commit();
			session.close();
		}
	}
	/**
	 * 删除用户收货地址
	 * @param code
	 */
	public void deleteAddredd(Integer code){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			iUserAddressMapper =session.getMapper(IUserAddressMapper.class);
			iUserAddressMapper.deleteAddredd(code);
		}finally{
			session.commit();
			session.close();
		}
	}
	/**
	 * 查询用户收货地址
	 * @param userCode
	 * @return
	 */
	public List<UserAddressBean> selectAddress(Integer userCode){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			iUserAddressMapper =session.getMapper(IUserAddressMapper.class);
			return iUserAddressMapper.selectAddress(userCode);
		}finally{
			session.close();
		}
	}
}
