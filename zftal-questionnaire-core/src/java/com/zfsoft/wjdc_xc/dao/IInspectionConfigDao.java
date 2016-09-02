package com.zfsoft.wjdc_xc.dao;

import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.wjdc_xc.entites.InspectionConfig;
import com.zfsoft.wjdc_xc.query.InspectionConfigQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public interface IInspectionConfigDao {
	public void addValue(InspectionConfig inspectionConfig);
	public void removeValue(InspectionConfig inspectionConfig);
	public List<String> findValue(InspectionConfig inspectionConfig);
	public List<String> findAppendValue(InspectionConfig inspectionConfig);
	public PageList<Map<String, Object>> getPagingInfoList(InspectionConfigQuery query);
	public int getPagingInfoCount(InspectionConfigQuery query);
}