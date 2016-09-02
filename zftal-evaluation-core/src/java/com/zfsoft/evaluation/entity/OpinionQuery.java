package com.zfsoft.evaluation.entity;

import com.zfsoft.dao.query.BaseQuery;


/**
 * 反馈意见查询类
 * @author Administrator
 *
 */
public class OpinionQuery extends BaseQuery{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2617155723240967435L;
	private String id;
	private String yjfk;
	private String yhid;
	private String yhm;
	private String fksj;
	private String rylx;
	private String zt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYjfk() {
		return yjfk;
	}
	public void setYjfk(String yjfk) {
		this.yjfk = yjfk;
	}
	public String getYhid() {
		return yhid;
	}
	public void setYhid(String yhid) {
		this.yhid = yhid;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getFksj() {
		return fksj;
	}
	public void setFksj(String fksj) {
		this.fksj = fksj;
	}
	public String getRylx() {
		return rylx;
	}
	public void setRylx(String rylx) {
		this.rylx = rylx;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	
}
