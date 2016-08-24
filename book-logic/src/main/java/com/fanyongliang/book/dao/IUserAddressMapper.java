package com.fanyongliang.book.dao;

import java.util.List;

import com.fanyongliang.book.beans.UserAddressBean;

/**
 *用户地址信息数据处理
 */
public interface IUserAddressMapper {
	/**
	 * 增加用户收货地址
	 * @param userAddressBean
	 */
	public void insertAddress(UserAddressBean userAddressBean);
	/**
	 * 更新用户收货地址
	 * @param userAddressBean
	 */
	public void updateAddress(UserAddressBean userAddressBean);
	/**
	 * 删除用户收货地址
	 * @param code
	 */
	public void deleteAddredd(Integer code);
	/**
	 * 查询用户收货地址
	 * @param userCode
	 * @return
	 */
	public List<UserAddressBean> selectAddress(Integer userCode);
}
