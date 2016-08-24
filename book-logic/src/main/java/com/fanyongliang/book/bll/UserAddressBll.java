package com.fanyongliang.book.bll;

import java.util.List;

import com.fanyongliang.book.beans.UserAddressBean;
import com.fanyongliang.book.dao.UserAddressDAO;
/**
 *用户收货地址管理业务类
 */
public class UserAddressBll {
	private UserAddressDAO userAddressDAO = new UserAddressDAO();
	/**
	 * 增加用户收货地址
	 * @param userAddressBean
	 */
	public void addAddress(UserAddressBean userAddressBean){
		userAddressDAO.insertAddress(userAddressBean);
	}
	/**
	 * 更新用户收货地址
	 * @param userAddressBean
	 */
	public void updateAddress(UserAddressBean userAddressBean){
		userAddressDAO.updateAddress(userAddressBean);
	}
	/**
	 * 删除用户收货地址
	 * @param code
	 */
	public void deleteAddredd(Integer code){
		userAddressDAO.deleteAddredd(code);
	}
	/**
	 * 查询用户收货地址
	 * @param userCode
	 * @return
	 */
	public List<UserAddressBean> getAddress(Integer userCode){
		return userAddressDAO.selectAddress(userCode);
	}
}
