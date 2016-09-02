package com.zfsoft.wjdc_xc.entites;

import com.zfsoft.orcus.lang.TimeUtil;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public class InspectionTaskMember {
	private String id;
	private String taskId;
	private String gh;
	
	private String rylx;
	private String xm;
	private String xy;
	private String zy;
	private String xzb;
	
	private String wjText;
	private String wjid;
	private String taskDate;
	private String configType;
	private Integer queryDayNum;
	/**
	 * 返回
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置
	 * @param id 
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 返回
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * 设置
	 * @param taskId 
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 返回
	 */
	public String getGh() {
		return gh;
	}
	/**
	 * 设置
	 * @param gh 
	 */
	public void setGh(String gh) {
		this.gh = gh;
	}
	/**
	 * 返回
	 */
	public String getWjText() {
		return wjText;
	}
	/**
	 * 设置
	 * @param wjText 
	 */
	public void setWjText(String wjText) {
		this.wjText = wjText;
	}
	/**
	 * 返回
	 */
	public String getWjid() {
		return wjid;
	}
	/**
	 * 设置
	 * @param wjid 
	 */
	public void setWjid(String wjid) {
		this.wjid = wjid;
	}
	/**
	 * 返回
	 */
	public String getTaskDate() {
		return taskDate;
	}
	/**
	 * 设置
	 * @param taskDate 
	 */
	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}
	public String getTaskDateStr() {
		if(taskDate==null) return "";
		return TimeUtil.format(taskDate, TimeUtil.yyyy_MM_dd);
	}
	/**
	 * 返回
	 */
	public Integer getQueryDayNum() {
		return queryDayNum;
	}
	/**
	 * 设置
	 * @param queryDayNum 
	 */
	public void setQueryDayNum(Integer queryDayNum) {
		this.queryDayNum = queryDayNum;
	}
	/**
	 * 返回
	 */
	public String getConfigType() {
		return configType;
	}
	/**
	 * 设置
	 * @param configType 
	 */
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getXzb() {
		return xzb;
	}
	public void setXzb(String xzb) {
		this.xzb = xzb;
	}
	public String getRylx() {
		return rylx;
	}
	public void setRylx(String rylx) {
		this.rylx = rylx;
	}
	
}
