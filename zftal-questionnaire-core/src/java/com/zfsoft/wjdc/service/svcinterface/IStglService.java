package com.zfsoft.wjdc.service.svcinterface;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.common.service.BaseService;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.wjdc.dao.entites.StglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.XxglModel;

public interface IStglService  extends BaseService<StglModel>{
	
	/**
	 * 保存编辑试题信息
	 * @param request
	 * @param model
	 * @return
	 */
	boolean saveEditStxx(HttpServletRequest request,WjglModel model) throws Exception;
	
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
	 * 保存问卷答案
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveWjDa(HttpServletRequest request,WjglModel model) throws Exception;
	
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
	 * 根据问卷id及试题id获取对应试题的答案结果（分页）
	 * @param stglModel
	 * @return
	 * @throws Exception
	 */
	public PageList<XxglModel> getTextPageListByStid(StglModel stglModel) throws Exception;

}
