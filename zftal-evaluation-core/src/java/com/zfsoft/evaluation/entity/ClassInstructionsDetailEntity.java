package com.zfsoft.evaluation.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* <p>Title: ClassInstructionsDetailEntity</p>
* <p>Description: 任课说明书详情实体类</p>
* <p>Company: XXXX</p>
* @author    rogerfan
* @date      2016-1-21
 */
public class ClassInstructionsDetailEntity implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	// ID
	private String id;
	// 任课说明书ID
	private String rksmsid;
	// 周次
	private String zc;
	// 教学时数
	private String jxss;
	// 教学内容
	private String jxnr;
	// 训练方式
	private String xlfs;
	// 相关英语知识训练
	private String yyzsxl;
	// 修改时间
	private Date xgsj;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the rksmsid
	 */
	public String getRksmsid() {
		return rksmsid;
	}
	/**
	 * @param rksmsid the rksmsid to set
	 */
	public void setRksmsid(String rksmsid) {
		this.rksmsid = rksmsid;
	}
	/**
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}
	/**
	 * @param zc the zc to set
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}
	/**
	 * @return the jxss
	 */
	public String getJxss() {
		return jxss;
	}
	/**
	 * @param jxss the jxss to set
	 */
	public void setJxss(String jxss) {
		this.jxss = jxss;
	}
	/**
	 * @return the jxnr
	 */
	public String getJxnr() {
		return jxnr;
	}
	/**
	 * @param jxnr the jxnr to set
	 */
	public void setJxnr(String jxnr) {
		this.jxnr = jxnr;
	}
	/**
	 * @return the xlfs
	 */
	public String getXlfs() {
		return xlfs;
	}
	/**
	 * @param xlfs the xlfs to set
	 */
	public void setXlfs(String xlfs) {
		this.xlfs = xlfs;
	}
	/**
	 * @return the yyzsxl
	 */
	public String getYyzsxl() {
		return yyzsxl;
	}
	/**
	 * @param yyzsxl the yyzsxl to set
	 */
	public void setYyzsxl(String yyzsxl) {
		this.yyzsxl = yyzsxl;
	}
	/**
	 * @return the xgsj
	 */
	public Date getXgsj() {
		return xgsj;
	}
	/**
	 * @param xgsj the xgsj to set
	 */
	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}
	
}
