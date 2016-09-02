package com.zfsoft.wjdc.dao.daointerface;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.common.dao.BaseDao;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.wjdc.dao.entites.StglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.XxglModel;
import com.zfsoft.wjdc.dao.entites.YhdjglModel;

public interface IStglDao  extends BaseDao<StglModel>{
	
	/**
	 * 删除试题大类信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int deleteStdlxx(WjglModel model) throws Exception;
	
	/**
	 * 插入试题大类信息
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertStdlxx(List list) throws Exception;
	
	/**
	 * 删除试题信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int deleteStxx(WjglModel model) throws Exception;
	
	/**
	 * 插入试题信息
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertStxx(List list) throws Exception;
	
	/**
	 * 删除试题选项信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int deleteXxxx(WjglModel model) throws Exception;
	
	/**
	 * 插入试题选项信息
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertXxxx(List list) throws Exception;
	
	/**
	 * 获取试题和试题大类排序后的列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<StglModel> getStxxAndStdlXxList(WjglModel model) throws Exception;
	
	/**
	 * 获取试题选项信息列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<XxglModel> getStXxxxList(WjglModel model) throws Exception;
	
	/**
	 * 获取试题信息列表
	 * @return
	 * @throws Exception
	 */
	public List<StglModel> getStxxList(WjglModel model) throws Exception;
	
	/**
	 * 删除问卷答案信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int deleteWjDaxx(WjglModel model) throws Exception;
	
	/**
	 * 插入问卷答案信息
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertWjDaxx(List list) throws Exception;
	
	/**
	 * 更改用户答卷状态
	 * @param yhdjglModel
	 * @return
	 * @throws Exception
	 */
	public int updateYhdjzt(YhdjglModel yhdjglModel) throws Exception;
	
	/**
	 * 获取问卷答案信息列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<XxglModel> getWjDaList(WjglModel model) throws Exception;
	
	/**
	 * 获取试题类型列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getStlxList() throws Exception;
	
	/**
	 * 获取用户答卷信息
	 * @param wjglModel
	 * @return
	 * @throws Exception
	 */
	public WjglModel getYhdjxx(WjglModel wjglModel) throws Exception;
	
	/**
	 * 更新问卷自动增加试题编号信息
	 * @param wjglModel
	 * @return
	 * @throws Exception
	 */
	public int updateWjxxAutoAddStbh(WjglModel wjglModel) throws Exception;
	/**
	 * 根据问卷id及试题id获取对应试题的答案结果（分页）
	 * @param stglModel
	 * @return
	 * @throws Exception
	 */
	public PageList<XxglModel> getTextPageListByStid(StglModel stglModel) throws Exception;
	/**
	 * 根据问卷id及试题id获取对应试题的总数（分页）
	 * @param stglModel
	 * @return
	 * @throws Exception
	 */
	public int getTextPageCountByStid(StglModel stglModel) throws Exception;
	

}
