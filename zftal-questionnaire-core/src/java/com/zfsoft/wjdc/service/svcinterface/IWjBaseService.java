package com.zfsoft.wjdc.service.svcinterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.common.service.BaseService;
import com.zfsoft.wjdc.dao.entites.StglModel;
import com.zfsoft.wjdc.dao.entites.WjffglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.WjpzModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.dao.entites.XxglModel;



public interface IWjBaseService extends BaseService<Object>{
	
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
	
	
	/**功能类别_问卷分发*/
	public final static String GNLB_WJFF="wjff";
	
	/**问卷答题情况统计*/
	public final static String GNLB_WJDTQKTJ="wjdtqktj";
	
	/**问卷答题详情*/
	public final static String GNLB_WJDTXQ="wjdtxq";
	
	/**问卷试题统计*/
	public final static String GNLB_WJSTTJ="wjsttj";
	
	/**问卷试题交叉统计*/
	public final static String GNLB_WJSTJCTJ="wjstjctj";
	
	public final static String[][] GNLBS={
		{GNLB_WJFF,"问卷分发"},
		{GNLB_WJDTXQ,"问卷答题详情"}/*,
		{GNLB_WJDTQKTJ,"问卷答题情况统计"},
		{GNLB_WJSTTJ,"问卷试题统计"},
		{GNLB_WJSTJCTJ,"问卷试题交叉统计"}*/
	 };
	
	/**
	 * 获取问卷配置_数据源类型列表_通用方法
	 * @return
	 * @throws Exception
	 */
	public List<WjpzSjylxModel> getWjpzSjylxList() throws Exception;
	
	/**
	 * 获取问卷配置_数据源类型_通用方法
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
	 * 获取功能类别查询条件配置列表_通用方法
	 * @param lxid 类型id
	 * @param gnlb 功能类别（请调用该接口中相对应的常量，例如：GNLB_WJFF）
	 * @return
	 */
	public List<WjpzModel> getGnlbCxtjPzList(WjpzModel model) throws Exception; 
	
	/**
	 * 获取功能类别查询结果配置列表_通用方法
	 * @param lxid 类型id
	 * @param gnlb 功能类别（请调用该接口中相对应的常量，例如：GNLB_WJFF）
	 * @return
	 */
	public List<WjpzModel> getGnlbCxjgPzList(WjpzModel model) throws Exception;

	/**
	 * 查询字段列表选项_通用方法
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdoptionList(WjpzModel model) throws Exception;
	
	/**
	 * 获取问卷对象查询表头列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjdxcxbtList(List<WjpzModel> list)throws Exception;
	
	/**
	 * 获取问卷发放对象查询结果列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjdxCxjgList(WjffglModel model,
			Map<String, String> sqlmap, List<WjpzModel> cxjgList, WjpzSjylxModel sjyModel)
			throws Exception;
	
	public List<String[]> getWjdxCxjgList(List<HashMap<String, String>> list, List<WjpzModel> cxjgList, String zj) throws Exception;
	/**
	 * 格式化REQUESTMAP内容
	 * @param map
	 * @return
	 */
	public Map<String, String> formatMap(Map<String, String[]> map);
	
	/**
	 * 获取已发放问卷对象结果列表
	 * @param model
	 * @param cxtjMap
	 * @param cxjgList
	 * @param sjyModel
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYffWjdxCxjgList(WjffglModel model,
			Map<String, String> cxtjMap, List<WjpzModel> cxjgList,
			WjpzSjylxModel sjyModel) throws Exception;
	
	/**
	 * 根据条件查询问卷对象表数据
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean getWjdxbsjByTj(Map<String, String> map) throws Exception;
	
	/**
	 * 格式化MAP转为str
	 * @param map
	 * @return
	 */
	public String formatMaptoStr(Map<String, String> map);
	
	
	/**拼装WHERE条件*/
	public String getWhereSql(Map<String, String> map);
	
	/**
	 * 获取问卷单个试题选项列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<XxglModel> getWjOneStXxList(StglModel model) throws Exception;
	
}
