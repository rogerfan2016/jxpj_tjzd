package com.zfsoft.wjdc.dao.daointerface;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.common.dao.BaseDao;
import com.zfsoft.wjdc.dao.entites.StglModel;
import com.zfsoft.wjdc.dao.entites.WjffglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.WjpzModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.dao.entites.XxglModel;

public interface IWjBaseDao extends BaseDao<Object> {
	
	/**获取问卷对象*/
	public WjglModel getWjglModel(WjglModel model) throws Exception;
	
	/**获取问卷约束功能路径*/
	public String getWjYsgnPath() throws Exception;
	
	/**获取问卷约束功能路径对应的问卷*/
	public List<WjglModel> getWjysgnPathWjList(HashMap<String, String> param) throws Exception;
	
	/**获取问卷类型列表*/
	public List<HashMap<String, String>> getWjlxList() throws Exception;
	
	/**获取问卷状态列表*/
	public List<HashMap<String, String>> getWjztList() throws Exception;
	
	
	/**
	 * 获取问卷配置_数据源类型列表
	 * @return
	 * @throws Exception
	 */
	public List<WjpzSjylxModel> getWjpzSjylxList() throws Exception;
	
	/**
	 * 获取问卷配置_数据源类型
	 * @param lxid
	 * @return
	 * @throws Exception
	 */
	public WjpzSjylxModel getWjpzSjylxModel(String lxid) throws Exception;
	
	/**
	 * 获取问卷配置_数据源类型_根据sessionlxid
	 * @param sessionlxid
	 * @return
	 * @throws Exception
	 */
	public WjpzSjylxModel getWjpzSjylxBySessionLxidModel(String sessionlxid) throws Exception;
	
	/**
	 * 获取功能类别查询条件配置列表
	 * @param lxid 类型id
	 * @param gnlb 功能类别
	 * @return
	 */
	public List<WjpzModel> getGnlbCxtjPzList(WjpzModel model) throws Exception; 
	
	/**
	 * 获取功能类别查询结果配置列表
	 * @param lxid 类型id
	 * @param gnlb 功能类别
	 * @return
	 */
	public List<WjpzModel> getGnlbCxjgPzList(WjpzModel model) throws Exception; 
	
	/**
	 * 查询条件字段值列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxtjzdzList(WjpzModel model) throws Exception;
	
	/**
	 * 查询问卷对象信息列表
	 * @param sqls
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPagedWjdxList(WjffglModel model) throws Exception;
	
	/**
	 * 根据条件查询问卷对象表数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String getWjdxbsjByTj(WjpzModel model)throws Exception;
	
	/**
	 * 获取问卷单个试题选项列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<XxglModel> getWjOneStXxList(StglModel model) throws Exception;
	
}
