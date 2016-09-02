package com.zfsoft.wjdc.dao.daointerface;

import java.util.HashMap;

import com.zfsoft.common.dao.BaseDao;
import com.zfsoft.wjdc.dao.entites.WjffglModel;

/**
 * 问卷分发管理DAO
 * @author Administrator
 *
 */
public interface IWjffglDao extends BaseDao<WjffglModel> {

	/**
	 * 删除问卷发放对象
	 * @param model
	 * @return
	 */
	public int batchDeleteWjffdx(HashMap<String, Object> map);

	/**
	 * 批量插入问卷发放对象
	 * @param map
	 * @return
	 */
	public int batchInsertWjffdx(HashMap<String, Object> map);
	
	/**
	 * 批量删除已发放问卷信息
	 * @param map
	 * @return
	 */
	public int batchDeleteYffwjffdx(HashMap<String, Object> map);
	
	/**
	 * 修改问卷发放标志
	 * @param map
	 * @return
	 */
	public int updateWjffdxbz(HashMap<String, Object> map);
	
	/**
	 * 根据条件下批量删除问卷发放对象
	 * @param model
	 * @return
	 */
	public int batchDeleteWjffdxBytj(WjffglModel model);
	
	/**
	 * 根据条件批量插入问卷对象
	 * @param model
	 * @return
	 */
	public int batchInsertWjffdxBytj(WjffglModel model);
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	public int updateWjffdxbzWff(HashMap<String, Object> map);
}
