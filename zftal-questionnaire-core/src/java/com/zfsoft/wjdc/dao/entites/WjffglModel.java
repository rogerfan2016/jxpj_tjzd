package com.zfsoft.wjdc.dao.entites;

import com.zfsoft.common.query.ModelBase;

/**
 * 问卷分发管理MODEL
 * @author ltt
 */
public class WjffglModel extends ModelBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String wjid;//问卷ID
	private String wjmc;//问卷名称
	private String wjlx;//问卷类型
	private String wjzt;//问卷状态
	private String ffrs;//分发人数
	private String cjrzgh;//创建人职工号
	private String cjrxm;//创建人姓名
	private String cjrbm;//创建人部门
	private String cjsj;//创建时间
	private String wjlxmc;//问卷类型名称
	private String cjkssj;//创建开始时间
	private String cjjssj;//创建结束时间
	private String lxid;//类型ID
	private String[] pkValue;
	private String zjz;//主键值
	private String pjdf;//平均得分
	private String djrs;//答卷人数
	
	private String sqls;//sqls
	
	public String getWjid() {
		return wjid;
	}
	public void setWjid(String wjid) {
		this.wjid = wjid;
	}
	public String getWjmc() {
		return wjmc;
	}
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}
	public String getWjlx() {
		return wjlx;
	}
	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}
	public String getWjzt() {
		return wjzt;
	}
	public void setWjzt(String wjzt) {
		this.wjzt = wjzt;
	}
	public String getFfrs() {
		return ffrs;
	}
	public void setFfrs(String ffrs) {
		this.ffrs = ffrs;
	}
	public String getCjrzgh() {
		return cjrzgh;
	}
	public void setCjrzgh(String cjrzgh) {
		this.cjrzgh = cjrzgh;
	}
	public String getCjrxm() {
		return cjrxm;
	}
	public void setCjrxm(String cjrxm) {
		this.cjrxm = cjrxm;
	}
	public String getCjrbm() {
		return cjrbm;
	}
	public void setCjrbm(String cjrbm) {
		this.cjrbm = cjrbm;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getWjlxmc() {
		return wjlxmc;
	}
	public void setWjlxmc(String wjlxmc) {
		this.wjlxmc = wjlxmc;
	}
	public String getCjkssj() {
		return cjkssj;
	}
	public void setCjkssj(String cjkssj) {
		this.cjkssj = cjkssj;
	}
	public String getCjjssj() {
		return cjjssj;
	}
	public void setCjjssj(String cjjssj) {
		this.cjjssj = cjjssj;
	}
	public String getLxid() {
		return lxid;
	}
	public void setLxid(String lxid) {
		this.lxid = lxid;
	}
	public String[] getPkValue() {
		return pkValue;
	}
	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}
	public String getZjz() {
		return zjz;
	}
	public void setZjz(String zjz) {
		this.zjz = zjz;
	}
	public String getSqls() {
		return sqls;
	}
	public void setSqls(String sqls) {
		this.sqls = sqls;
	}
	/**
	 * 返回
	 */
	public String getPjdf() {
		return pjdf;
	}
	/**
	 * 设置
	 * @param pjdf 
	 */
	public void setPjdf(String pjdf) {
		this.pjdf = pjdf;
	}
	/**
	 * 返回
	 */
	public String getDjrs() {
		return djrs;
	}
	/**
	 * 设置
	 * @param djrs 
	 */
	public void setDjrs(String djrs) {
		this.djrs = djrs;
	}
	
}
