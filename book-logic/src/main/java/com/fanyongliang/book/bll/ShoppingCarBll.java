package com.fanyongliang.book.bll;

import java.util.List;

import com.fanyongliang.book.beans.ShoppingCarBean;
import com.fanyongliang.book.dao.ShoppingCarDAO;
/**
 * 购物车逻辑实现类
 * @author fanyongliang
 */
public class ShoppingCarBll {
	private ShoppingCarDAO shoppingCarDAO = new ShoppingCarDAO();
	/**
	 * 购物车表增加数据
	 * @param schoppingCarBean
	 */
	public void insertToCar(ShoppingCarBean shoppingCarBean){
		ShoppingCarBean selectCarBean = 
				shoppingCarDAO.selectCarByBookCode(shoppingCarBean);
		//如果原来没有有本本物品就插入，不然就于原来的书本合并
		if(selectCarBean == null){
			shoppingCarDAO.insertToCar(shoppingCarBean);
		}else{
			selectCarBean.setBookQuantity(selectCarBean.getBookQuantity()
					+shoppingCarBean.getBookQuantity());
			shoppingCarDAO.updateCarByCode(selectCarBean);
		}
	}
	/**	 
	 * 购物车表删除一条数据
	 * @param carCode
	 */
	public void deleteToCar(Integer carCode){
		shoppingCarDAO.deleteToCar(carCode);
	}
	/**
	 * 购物车表全部删除
	 * @param userCode
	 */
	public void deleteAllToCar(Integer userCode){
		shoppingCarDAO.deleteAllToCar(userCode);
	}
	/**
	 * 购物车表查询数据
	 * @param userCode
	 */
	public List<ShoppingCarBean> selectToCar(Integer userCode){
		return shoppingCarDAO.selectToCar(userCode);
	}
	/**
	 * 更新表中的某一个数据 
	 * @param schoppingCarBean
	 */
	public void updateCarByCode(ShoppingCarBean schoppingCarBean){
		shoppingCarDAO.updateCarByCode(schoppingCarBean);
	}
}
