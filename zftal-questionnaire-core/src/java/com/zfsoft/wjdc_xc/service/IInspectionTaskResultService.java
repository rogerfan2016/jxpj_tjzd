package com.zfsoft.wjdc_xc.service;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.wjdc_xc.entites.InspectionTaskResult;
import com.zfsoft.wjdc_xc.query.InspectionTaskResultQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-12
 * @version V1.0.0
 */
public interface IInspectionTaskResultService {
	public PageList<InspectionTaskResult> getPagingList(InspectionTaskResultQuery query);

	public void insert(InspectionTaskResult inspectionTask);
	
	public void updateStatus(InspectionTaskResult inspectionTask);

	public InspectionTaskResult findById(String key);

	void remove(InspectionTaskResult inspectionTask) throws Exception;
	
	public int findCount(InspectionTaskResultQuery query);
	
	public int getFzSum(String id);
}
