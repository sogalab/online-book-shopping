package com.fanyongliang.book.dao;

import java.util.List;

import com.fanyongliang.book.beans.AdvPhotoBean;


public interface IAdvPhotoMapper {
	/**
	 * 查询所有广告信息
	 * 
	 * @param
	 * @return
	 */
	public List<AdvPhotoBean> selectAllAdvPhoto();
	/**
	 * 修改广告
	 * 
	 * @param
	 * @return
	 */
	public void updateAdvPhoto(AdvPhotoBean advPhotoBean);
	/**
	 * 新增广告
	 * 
	 * @param
	 * @return
	 */
	public void addAdvPhoto(AdvPhotoBean advPhotoBean);
	/**
	 * 删除广告
	 * 
	 * @param
	 * @return
	 */
	public void deleteAdvPhoto(Integer code);
}
