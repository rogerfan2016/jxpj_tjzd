package com.zfsoft.wjdc_xc.service.impl;

import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dao.page.Paginator;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc_xc.dao.IInspectionConfigDao;
import com.zfsoft.wjdc_xc.entites.InspectionConfig;
import com.zfsoft.wjdc_xc.query.InspectionConfigQuery;
import com.zfsoft.wjdc_xc.service.IInspectionConfigService;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-5
 * @version V1.0.0
 */
public class InspectionConfigServiceImpl implements IInspectionConfigService {
	
	private IInspectionConfigDao inspectionConfigDao;
	@Override
	public PageList<Map<String, Object>> getPagingPersonList(InspectionConfigQuery query) {
		query.setTable("overall");
		String param = "";
		if(StringUtil.isNotEmpty(query.getParams().get("gh"))){
			param+=" and gh =#{params.gh}";
		}
		if(StringUtil.isNotEmpty(query.getParams().get("xm"))){
			param+=" and xm like '%'||#{params.xm}||'%'";
		}
		query.setExpress(" gh not in (select config_val from XC_CONFIG where config_key='personList' and config_type=#{params.type}) "+param);
		return getPagingList(query);
	}
	
	private PageList<Map<String, Object>> getPagingList(InspectionConfigQuery query) {
//		return inspectionConfigDao.getPagedInfoList(query);
		PageList<Map<String, Object>> pageList = new PageList<Map<String, Object>>();
		Paginator paginator = new Paginator();
		if(query!=null){
			paginator.setItemsPerPage(query.getPerPageSize());
			paginator.setPage((Integer)query.getToPage());
			
			paginator.setItems(inspectionConfigDao.getPagingInfoCount(query));
			pageList.setPaginator(paginator);
			
			if(paginator.getBeginIndex() <= paginator.getItems()){
				query.setStartRow(paginator.getBeginIndex());
				query.setEndRow(paginator.getEndIndex());
				List<Map<String, Object>> list = inspectionConfigDao.getPagingInfoList(query);
				pageList.addAll(list);
			}
		}
		return pageList;
	}

	@Override
	public void addValue(InspectionConfig inspectionConfig) {
		inspectionConfigDao.addValue(inspectionConfig);
	}
	@Override
	public void removeValue(InspectionConfig inspectionConfig) {
		inspectionConfigDao.removeValue(inspectionConfig);
	}
	@Override
	public List<String> findValue(InspectionConfig inspectionConfig) {
		return inspectionConfigDao.findValue(inspectionConfig);
	}
	@Override
	public List<String> findAppendValue(InspectionConfig inspectionConfig) {
		return inspectionConfigDao.findAppendValue(inspectionConfig);
	}

	/**
	 * 设置
	 * @param inspectionConfigDao 
	 */
	public void setInspectionConfigDao(IInspectionConfigDao inspectionConfigDao) {
		this.inspectionConfigDao = inspectionConfigDao;
	}

	@Override
	public List<Map<String, Object>> getCheckedDcwjList(String type) {
		InspectionConfigQuery query = new InspectionConfigQuery();
		query.setTotalItem(1000);
		query.setEndRow(1000);
		query.setTable("wjdc_wjxxb");
		query.getParams().put("type", type);
		query.setExpress(" wjid in (select config_val from XC_CONFIG where config_key='dcwjList' and config_type=#{params.type})");
		return inspectionConfigDao.getPagingInfoList(query);
	}

	@Override
	public List<Map<String, Object>> getCheckedPersonList(String type) {
		InspectionConfigQuery query = new InspectionConfigQuery();
		query.setTotalItem(1000);
		query.setEndRow(1000);
		query.setTable("overall");
		query.getParams().put("type", type);
		query.setExpress(" gh in (select config_val from XC_CONFIG where config_key='personList' and config_type=#{params.type})");
		return inspectionConfigDao.getPagingInfoList(query);
	}

	@Override
	public PageList<Map<String, Object>> getPagingDcwjList(
			InspectionConfigQuery query) {
		query.setTable("wjdc_wjxxb");
		String param = "";
		if(StringUtil.isNotEmpty(query.getParams().get("wjlx"))){
			param+=" and wjlx =#{params.wjlx}";
		}
		if(StringUtil.isNotEmpty(query.getParams().get("wjzt"))){
			param+=" and wjzt =#{params.wjzt}";
		}
		if(StringUtil.isNotEmpty(query.getParams().get("wjmc"))){
			param+=" and wjmc like '%'||#{params.wjmc}||'%'";
		}
		query.setExpress(" wjid not in (select config_val from XC_CONFIG where config_key='dcwjList' and config_type=#{params.type}) "+param);
		return getPagingList(query);
	}

}
