package com.fanyongliang.book.dao;

import java.util.List;

import com.fanyongliang.book.beans.ShoppingCarBean;

/**
 * 购物车数据处理类
 */
public interface IShoppingCarMapper {
	/**
	 * 购物车表增加数据
	 * @param schoppingCarBean
	 */
	public void insertToCar(ShoppingCarBean schoppingCarBean);
	/**
	 * 购物车表删除一条数据
	 * @param carCode
	 */
	public void deleteToCar(Integer carCode);
	/**
	 * 购物车表全部删除
	 * @param userCode
	 */
	public void deleteAllToCar(Integer userCode);
	/**
	 * 购物车表查询数据
	 * @param userCode
	 */
	public List<ShoppingCarBean> selectToCar(Integer userCode);
	/**
	 * 查询购物城中的数据根据书本Code
	 * @param schoppingCarBean
	 * @return ShoppingCarBean
	 */
	public ShoppingCarBean selectCarByBookCode(ShoppingCarBean schoppingCarBean);
	/**
	 * 更新表中的某一个数据 
	 * @param schoppingCarBean
	 */
	public void updateCarByCode(ShoppingCarBean schoppingCarBean);
}
