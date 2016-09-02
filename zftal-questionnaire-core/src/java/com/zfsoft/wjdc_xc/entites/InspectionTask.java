package com.zfsoft.wjdc_xc.entites;

import java.util.Date;
import java.util.List;

import com.zfsoft.orcus.lang.TimeUtil;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public class InspectionTask {
	private String id;
	private String wjid;
	private String configType;
	private Date taskDate;
	private String zt;
	
	private Integer dxNum;
	private Integer memberNum;
	
	private String wjText;
	private String userId;
	private List<InspectionTaskMember> memberList;
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
	public Date getTaskDate() {
		return taskDate;
	}
	/**
	 * 返回
	 */
	public String getTaskDateStr() {
		if(taskDate==null) return "";
		return TimeUtil.format(taskDate, TimeUtil.yyyy_MM_dd);
	}
	/**
	 * 设置
	 * @param taskDate 
	 */
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
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
	public List<InspectionTaskMember> getMemberList() {
		return memberList;
	}
	/**
	 * 设置
	 * @param memberList 
	 */
	public void setMemberList(List<InspectionTaskMember> memberList) {
		this.memberList = memberList;
	}
	/**
	 * 返回
	 */
	public Integer getDxNum() {
		return dxNum;
	}
	/**
	 * 设置
	 * @param dxNum 
	 */
	public void setDxNum(Integer dxNum) {
		this.dxNum = dxNum;
	}
	/**
	 * 返回
	 */
	public Integer getMemberNum() {
		return memberNum;
	}
	/**
	 * 设置
	 * @param memberNum 
	 */
	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	
}
