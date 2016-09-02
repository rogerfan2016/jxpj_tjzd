package com.zfsoft.wjdc_xc.query;

import java.util.Date;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-12
 * @version V1.0.0
 */
public class InspectionTaskResultQuery extends BaseQuery{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5938476813381316016L;
	private String dcdx;
	private String gh;
	private String memberId;
	private String status;
	private String configType;
	private String dcdxText;
	private Date start;
	private Date end;
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
	public Date getStart() {
		return start;
	}
	/**
	 * 设置
	 * @param start 
	 */
	public void setStart(Date start) {
		this.start = start;
	}
	/**
	 * 返回
	 */
	public Date getEnd() {
		return end;
	}
	/**
	 * 设置
	 * @param end 
	 */
	public void setEnd(Date end) {
		this.end = end;
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

}
