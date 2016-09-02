package com.zfsoft.wjdc.dao.entites;

public class XxglModel {
	
	private String wjid;//问卷id
	private String stid;//试题id
	private String xxid ;//选项id--对于矩阵题，行列选项要使用特殊的标记进行处理row、col作为后缀
    private String xxbh ;//选项编号
    private String xxmc ;//选项名称
    private String xxfz ;//选项分值
    private String sfklr ;//是否可录入
    private String xssx ;//显示顺序
    
    //问卷回答部分使用的字段
    private String djrid;//答卷人id
    private String txnr;//填写内容--用于问答题、组合题、矩阵题
    private String plsx;//排列顺序--用于排序题
    
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
	public String getXxid() {
		return xxid;
	}
	public void setXxid(String xxid) {
		this.xxid = xxid;
	}
	public String getXxbh() {
		return xxbh;
	}
	public void setXxbh(String xxbh) {
		this.xxbh = xxbh;
	}
	public String getXxmc() {
		return xxmc;
	}
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	public String getXxfz() {
		return xxfz;
	}
	public void setXxfz(String xxfz) {
		this.xxfz = xxfz;
	}
	public String getSfklr() {
		return sfklr;
	}
	public void setSfklr(String sfklr) {
		this.sfklr = sfklr;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getDjrid() {
		return djrid;
	}
	public void setDjrid(String djrid) {
		this.djrid = djrid;
	}
	public String getTxnr() {
		return txnr;
	}
	public void setTxnr(String txnr) {
		this.txnr = txnr;
	}
	public String getPlsx() {
		return plsx;
	}
	public void setPlsx(String plsx) {
		this.plsx = plsx;
	}

    
}
