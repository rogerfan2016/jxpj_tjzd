package com.zfsoft.wjdc_xc.entites;

import java.util.Date;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public class InspectionTaskResult {
	private String id;
	private String memberId;
	private String dcdx;
	private String status;
	private String wjid;
	private String wjText;
	private String dcdxText;
	private Date taskDate;
	private String wjzt;
	private String djzt;
	private String rwzt;// 任务状态
	private String dcr;// 调查人
	private String zf;// 总得分
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
	public String getMemberId() {
		return memberId;
	}
	/**
	 * 设置
	 * @param memberId 
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	/**
	 * 返回
	 */
	public String getDcdx() {
		return dcdx;
	}
	/**
	 * 设置
	 * @param dcdx 
	 */
	public void setDcdx(String dcdx) {
		this.dcdx = dcdx;
	}
	/**
	 * 返回
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置
	 * @param status 
	 */
	public void setStatus(String status) {
		this.status = status;
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
	public String getDcdxText() {
		return dcdxText;
	}
	/**
	 * 设置
	 * @param dcdxText 
	 */
	public void setDcdxText(String dcdxText) {
		this.dcdxText = dcdxText;
	}
	/**
	 * 返回
	 */
	public Date getTaskDate() {
		return taskDate;
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
	public String getWjzt() {
		return wjzt;
	}
	/**
	 * 设置
	 * @param wjzt 
	 */
	public void setWjzt(String wjzt) {
		this.wjzt = wjzt;
	}
	/**
	 * 返回
	 */
	public String getDjzt() {
		return djzt;
	}
	/**
	 * 设置
	 * @param djzt 
	 */
	public void setDjzt(String djzt) {
		this.djzt = djzt;
	}
	public String getRwzt() {
		return rwzt;
	}
	public void setRwzt(String rwzt) {
		this.rwzt = rwzt;
	}
	public String getDcr() {
		return dcr;
	}
	public void setDcr(String dcr) {
		this.dcr = dcr;
	}
	public String getZf() {
		return zf;
	}
	public void setZf(String zf) {
		this.zf = zf;
	}
	
}
