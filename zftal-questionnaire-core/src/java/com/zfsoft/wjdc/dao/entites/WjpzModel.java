package com.zfsoft.wjdc.dao.entites;

import com.zfsoft.common.query.ModelBase;

public class WjpzModel extends ModelBase{
	
	private static final long serialVersionUID = 1L;
	
	private String lxid;//类型id
	private String gnlb;//功能类别
	private String zd;//字段
	private String xssx;//显示顺序
	private String sfqy;//是否启用
	private String bm;//表名
	private String zdmc;//字段名称
	private String bqlx;//标签类型
	private String ylzd;//依赖字段（例如：地区配置省市）
	private String yszd;//约束字段（例如：省市配置地区）
	private String sqls;//
	private String[] fields;//字段数组
	
	public String getLxid() {
		return lxid;
	}
	public void setLxid(String lxid) {
		this.lxid = lxid;
	}
	public String getGnlb() {
		return gnlb;
	}
	public void setGnlb(String gnlb) {
		this.gnlb = gnlb;
	}
	public String getZd() {
		return zd;
	}
	public void setZd(String zd) {
		this.zd = zd;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	public String getZdmc() {
		return zdmc;
	}
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
	public String getBqlx() {
		return bqlx;
	}
	public void setBqlx(String bqlx) {
		this.bqlx = bqlx;
	}
	public String getYlzd() {
		return ylzd;
	}
	public void setYlzd(String ylzd) {
		this.ylzd = ylzd;
	}
	public String getYszd() {
		return yszd;
	}
	public void setYszd(String yszd) {
		this.yszd = yszd;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getSqls() {
		return sqls;
	}
	public void setSqls(String sqls) {
		this.sqls = sqls;
	}
	public final String[] getFields() {
		return fields;
	}
	public final void setFields(String[] fields) {
		this.fields = fields;
	}
	

}
