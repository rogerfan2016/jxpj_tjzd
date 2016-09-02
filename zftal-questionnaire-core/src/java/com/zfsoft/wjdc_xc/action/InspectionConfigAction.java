package com.zfsoft.wjdc_xc.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.zfsoft.common.spring.SpringHolder;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.normal.info.entity.OverallInfo;
import com.zfsoft.hrm.normal.info.service.svcinterface.IOverallInfoService;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc_xc.entites.InspectionConfig;
import com.zfsoft.wjdc_xc.query.InspectionConfigQuery;
import com.zfsoft.wjdc_xc.service.IInspectionConfigService;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public class InspectionConfigAction extends HrmAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7091496556422483536L;
	private InspectionConfig inspectionConfig = new InspectionConfig();
	private String[] valueList;
	private InspectionConfigQuery query = new InspectionConfigQuery();
	private IInspectionConfigService inspectionConfigService;
	private String type="XNXC";
	public String setup(){
		
		getValueStack().set("personList", inspectionConfigService.getCheckedPersonList(type));
		
		getValueStack().set("dcwjList", inspectionConfigService.getCheckedDcwjList(type));
		return "setup";
	} 
	
	/**
	* <p>Title: pj_setup</p>
	* <p>Description: 评价设置</p>
	* @return
	 */
	public String pj_setup(){
		return "pj_setup";
	}
	
	public String dcwjList(){
		getValueStack().set("list", inspectionConfigService.getPagingDcwjList(query));
		return "dcwjList";
	}
	public String personList(){
		getValueStack().set("list", inspectionConfigService.getPagingPersonList(query));
		return "personList";
	}
	public String save(){
		if(valueList!=null){
			for (String v : valueList) {
				inspectionConfig.setValue(v);
				inspectionConfigService.removeValue(inspectionConfig);
				inspectionConfigService.addValue(inspectionConfig);
			}
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}
	public String remove(){
		if(StringUtil.isNotEmpty(inspectionConfig.getValue())){
			inspectionConfigService.removeValue(inspectionConfig);
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}

	public String userListScopeThink() throws UnsupportedEncodingException{
		InspectionConfig bean = new InspectionConfig();
		bean.setConfigType(type);
		bean.setKey("sql_append");
		List<String> list = inspectionConfigService.findValue(bean);
		String sql = "1=1";
		if(list!=null&&list.size()>0){
			sql=list.get(0);
			sql=sql.replaceAll("\\$\\{[^\\}]*\\}", "'"+getUser().getYhm()+"'");
			
		}
		IOverallInfoService overallInfoService = SpringHolder.getBean("overallInfoService", IOverallInfoService.class);
		String term=new String(getRequest().getParameter("term").getBytes(getRequest().getCharacterEncoding()),"utf-8");
//		String dept =getRequest().getParameter("dept");
		int num =10;
		try {
			num=Integer.valueOf(getRequest().getParameter("maxItemSize"));
		} catch (Exception e) {
		}
//		String depSql="OFF".equals(dept)?"":" and "+DeptFilterUtil.getCondition("orl", "dwm");
		List<OverallInfo> overallInfos=overallInfoService.userListThink(term," rownum<="+num+" and "+sql);
		this.getValueStack().set(DATA, overallInfos);
		return DATA;
	}

	/**
	 * 设置
	 * @param inspectionConfigService 
	 */
	public void setInspectionConfigService(
			IInspectionConfigService inspectionConfigService) {
		this.inspectionConfigService = inspectionConfigService;
	}

	/**
	 * 返回
	 */
	public InspectionConfigQuery getQuery() {
		return query;
	}

	/**
	 * 设置
	 * @param query 
	 */
	public void setQuery(InspectionConfigQuery query) {
		this.query = query;
	}

	/**
	 * 返回
	 */
	public InspectionConfig getInspectionConfig() {
		return inspectionConfig;
	}

	/**
	 * 设置
	 * @param inspectionConfig 
	 */
	public void setInspectionConfig(InspectionConfig inspectionConfig) {
		this.inspectionConfig = inspectionConfig;
	}

	/**
	 * 返回
	 */
	public String[] getValueList() {
		return valueList;
	}

	/**
	 * 设置
	 * @param valueList 
	 */
	public void setValueList(String[] valueList) {
		this.valueList = valueList;
	}

	/**
	 * 设置
	 * @param type 
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 返回
	 */
	public String getType() {
		return type;
	}
}