package com.fanyongliang.book.bll;

import com.fanyongliang.book.dao.utility.MD5;

/**
 * 密码加密实现类
 */
public class MD5BLL {
	/**
	 * 实现对传来的密码进行MD5加密
	 * @param password
	 * @return String[] str[0]: key str[1]:加密后的密码
	 */
	public String[] changePassword(String password) {
		char[] charArray = new char[4];
		for(int i=0;i<charArray.length;i++){
			int a = (int) (Math.random()*126);
			charArray[i] = (char) a;
		}
		String seed = String.valueOf(charArray);
		String newPassword = MD5.encode(password) + seed;
		String [] result = {seed,MD5.encode(newPassword)};
		return result;
	}
	/**
	 * 实现对传来的密码进行MD5加密,包含key
	 * @param password key
	 * @return String:加密后的密码
	 */
	public String getPassword(String password,String key) {
		String newPassword = MD5.encode(password) + key;
		String result = MD5.encode(newPassword);
		return result;
	}
}
