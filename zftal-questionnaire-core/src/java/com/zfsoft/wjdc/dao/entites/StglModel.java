package com.zfsoft.wjdc.dao.entites;

import com.zfsoft.common.query.ModelBase;

public class StglModel extends ModelBase{
	
	private String wjid;//问卷id
	private String stid;//试题id
	private String stmc;//试题名称
	private String stlx;//试题类型
	private String stlxmc;//试题类型名称
	private String dhxxgs;//单行选项个数
	private String sfbd;//是否必答
	private String stzf;//试题总分
	private String xssx;//显示顺序
	private String stdlid;//试题大类代码
	private String xxkzdxzs;//选项可最多选择数
	private String stbh;//试题编号
	
	public String getWjid() {
		return wjid;
	}
	public void setWjid(String wjid) {
		this.wjid = wjid;
	}
	public String getStid() {
		return stid;
	}
	public void setStid(String stid) {
		this.stid = stid;
	}
	public String getStmc() {
		return stmc;
	}
	public void setStmc(String stmc) {
		this.stmc = stmc;
	}
	public String getStlx() {
		return stlx;
	}
	public void setStlx(String stlx) {
		this.stlx = stlx;
	}
	public String getDhxxgs() {
		return dhxxgs;
	}
	public void setDhxxgs(String dhxxgs) {
		this.dhxxgs = dhxxgs;
	}
	public String getSfbd() {
		return sfbd;
	}
	public void setSfbd(String sfbd) {
		this.sfbd = sfbd;
	}
	public String getStzf() {
		return stzf;
	}
	public void setStzf(String stzf) {
		this.stzf = stzf;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getStdlid() {
		return stdlid;
	}
	public void setStdlid(String stdlid) {
		this.stdlid = stdlid;
	}
	public final String getStlxmc() {
		return stlxmc;
	}
	public final void setStlxmc(String stlxmc) {
		this.stlxmc = stlxmc;
	}
	public final String getXxkzdxzs() {
		return xxkzdxzs;
	}
	public final void setXxkzdxzs(String xxkzdxzs) {
		this.xxkzdxzs = xxkzdxzs;
	}
	public final String getStbh() {
		return stbh;
	}
	public final void setStbh(String stbh) {
		this.stbh = stbh;
	}
	
	
}
