package com.zfsoft.wjdc_xc.service;
import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.wjdc_xc.entites.InspectionConfig;
import com.zfsoft.wjdc_xc.query.InspectionConfigQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-5
 * @version V1.0.0
 */
public interface IInspectionConfigService {
	
	public PageList<Map<String, Object>> getPagingPersonList(InspectionConfigQuery query);
	public PageList<Map<String, Object>> getPagingDcwjList(InspectionConfigQuery query);
	public List<Map<String, Object>> getCheckedPersonList(String type);
	public List<Map<String, Object>> getCheckedDcwjList(String type);
	public void addValue(InspectionConfig inspectionConfig);
	public List<String> findValue(InspectionConfig inspectionConfig);
	public List<String> findAppendValue(InspectionConfig inspectionConfig);
	void removeValue(InspectionConfig inspectionConfig);
}
