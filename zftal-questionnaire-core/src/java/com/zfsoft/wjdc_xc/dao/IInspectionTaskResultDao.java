package com.zfsoft.wjdc_xc.dao;

import java.util.List;

import com.zfsoft.wjdc_xc.entites.InspectionTaskResult;
import com.zfsoft.wjdc_xc.query.InspectionTaskResultQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public interface IInspectionTaskResultDao {
	public void insert(InspectionTaskResult inspectionTaskResult);
	public void update(InspectionTaskResult inspectionTaskResult);
	public InspectionTaskResult findById(String id);
	public void remove(InspectionTaskResult inspectionTaskResult);
	public List<InspectionTaskResult> getPagingInfoList(InspectionTaskResultQuery query);
	public int getPagingInfoCount(InspectionTaskResultQuery query);
	public int getFzSum(String id);
}
