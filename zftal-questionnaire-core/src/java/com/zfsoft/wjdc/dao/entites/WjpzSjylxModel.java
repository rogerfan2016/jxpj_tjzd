package com.zfsoft.wjdc.dao.entites;

public class WjpzSjylxModel {
	
	private String lxid;//类型id
	private String lxmc;//类型名称
	private String bm;//表名
	private String zj;//主键
	private String xssx;//显示顺序
	private String sfqy;//是否启用
	private String sessionlxid;//系统中session中放置的用户类型，其实可以将lxid配置成与其一致即可
	
	public String getLxid() {
		return lxid;
	}
	public void setLxid(String lxid) {
		this.lxid = lxid;
	}
	public String getLxmc() {
		return lxmc;
	}
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getZj() {
		return zj;
	}
	public void setZj(String zj) {
		this.zj = zj;
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
	public final String getSessionlxid() {
		return sessionlxid;
	}
	public final void setSessionlxid(String sessionlxid) {
		this.sessionlxid = sessionlxid;
	}
	
	

}
