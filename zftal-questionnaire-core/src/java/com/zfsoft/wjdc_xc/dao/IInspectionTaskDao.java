package com.zfsoft.wjdc_xc.dao;

import java.util.List;
import java.util.Map;

import com.zfsoft.wjdc_xc.entites.InspectionTask;
import com.zfsoft.wjdc_xc.entites.InspectionTaskMember;
import com.zfsoft.wjdc_xc.query.InspectionSummerQuery;
import com.zfsoft.wjdc_xc.query.InspectionTaskQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public interface IInspectionTaskDao {

	public void insert(InspectionTask inspectionTask);
	public void remove(String id);
	public void update(InspectionTask inspectionTask);
	public InspectionTask findById(String id);
	public List<InspectionTask> getPagingInfoList(InspectionTaskQuery inspectionTaskQuery);
	public int getPagingInfoCount(InspectionTaskQuery inspectionTaskQuery);
	
	public List<InspectionTaskMember> getMemberList(InspectionTaskMember member);
	public void removeMember(InspectionTaskMember member);
	public void insertMember(InspectionTaskMember member);
	
	public List<Map<String, Object>> getTaskResultSummary(InspectionSummerQuery query);
	
	public List<Map<String, Object>> getTaskSummerPage(InspectionSummerQuery query);
	public int getTaskSummerPageCount(InspectionSummerQuery query);
	
	public List<Map<String, Object>> getXcdxSummerPage(InspectionSummerQuery query);
	public int getXcdxSummerPageCount(InspectionSummerQuery query);
	/**
	 * 
	* @Title: getTaskMemberCount 
	* @Description: TODO(获取评价成员 列表数量) 
	* @param @param query
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getTaskMemberCount(InspectionTaskQuery query);
	
	/**
	 * 
	* @Title: getTaskMemberList 
	* @Description: TODO(获取评价成员 列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return List<InspectionTaskMember>    返回类型 
	* @throws
	 */
	public List<InspectionTaskMember> getTaskMemberList(InspectionTaskQuery query);
}