package com.zfsoft.wjdc.dao.daointerface;

import java.util.List;

import com.zfsoft.common.dao.BaseDao;
import com.zfsoft.wjdc.dao.entites.WjpzModel;

public interface IWjpzDao extends BaseDao<WjpzModel> {
	
	/**
	 * 获取功能类别查询条件配置列表(用于问卷设置配置信息)
	 * @return
	 */
	public List<WjpzModel> getGnlbCxtjPzAllList(WjpzModel model) throws Exception; 
	
	/**
	 * 获取功能类别查询结果配置列表(用于问卷设置配置信息)
	 * @return
	 */
	public List<WjpzModel> getGnlbCxjgPzAllList(WjpzModel model) throws Exception;
	
	/**
	 * 删除查询条件配置信息
	 * @throws Exception
	 */
	public int deleteCxtjPzxx(WjpzModel model) throws Exception;
	
	/**
	 * 插入查询条件配置信息
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertCxtjPzxx(List<WjpzModel> list) throws Exception;
	
	/**
	 * 删除查询结果配置信息
	 * @throws Exception
	 */
	public int deleteCxjgPzxx(WjpzModel model) throws Exception;
	
	/**
	 * 插入查询结果配置信息
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertCxjgPzxx(List<WjpzModel> list) throws Exception;

}
