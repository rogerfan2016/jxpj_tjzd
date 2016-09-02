package com.zfsoft.wjdc_xc.service;

import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.hrm.normal.info.entity.OverallInfo;
import com.zfsoft.wjdc_xc.entites.InspectionTask;
import com.zfsoft.wjdc_xc.entites.InspectionTaskMember;
import com.zfsoft.wjdc_xc.query.InspectionSummerQuery;
import com.zfsoft.wjdc_xc.query.InspectionTaskQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-10
 * @version V1.0.0
 */
public interface IInspectionTaskService {
	
	public PageList<InspectionTask> getPagingList(InspectionTaskQuery query);

	public void save(InspectionTask inspectionTask,List<OverallInfo> overallInfoList);
	
	public void update(InspectionTask inspectionTask);

	public InspectionTask findById(String key);

	void remove(InspectionTask inspectionTask);

	public List<InspectionTaskMember> getMemberList(InspectionTaskMember query);

	PageList<Map<String, Object>> getTaskSummerPage(InspectionSummerQuery query);
	
	public List<Map<String, Object>> getTaskResultSummary(InspectionSummerQuery query);

	PageList<Map<String, Object>> getResultPagingList(InspectionSummerQuery query);
	
	/**
	 * 
	* @Title: getTaskMemberList 
	* @Description: TODO(获取评价成员 列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return PageList<InspectionTaskMember>    返回类型 
	* @throws
	 */
	public PageList<InspectionTaskMember> getTaskMemberList(InspectionTaskQuery query);
}
