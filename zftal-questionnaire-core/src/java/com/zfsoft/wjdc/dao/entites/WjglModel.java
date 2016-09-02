package com.zfsoft.wjdc.dao.entites;

import com.zfsoft.common.query.ModelBase;

public class WjglModel extends ModelBase{
	
	private static final long serialVersionUID = 1L;
	private String wjid ;//问卷id
    private String wjmc ;//问卷名称
    private String wjlx ;//问卷类型
    private String wjlxmc;//问卷类型名称
    private String wjzt ;//问卷状态
    private String jsy ;//卷首语
    private String jwy ;//卷尾语
    private String wjzf ;//问卷总分
    private String cjr ;//创建人
    private String dags;//单行选项个数
    private String cjsj;//创建时间
    private String gqsj;//获取时间
    private String autoaddstbh;//自动增加试题编号
    
    private StglModel[] stModel;//试题对象集合，内部包含试题大类和试题
    private String djrid;//答卷人id
    private String djzt;//答卷状态
    private String cjrmc;//创建人名称
    
    private String pjid;//评价ID（使用问卷调查的业务ID）
    
    public String getPjid() {
		return pjid;
	}

	public void setPjid(String pjid) {
		this.pjid = pjid;
	}

	private String modelId;
    
    private String[] ysgndms;//约束功能代码

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

	public String getJsy() {
		return jsy;
	}

	public void setJsy(String jsy) {
		this.jsy = jsy;
	}

	public String getJwy() {
		return jwy;
	}

	public void setJwy(String jwy) {
		this.jwy = jwy;
	}

	public String getWjzf() {
		return wjzf;
	}

	public void setWjzf(String wjzf) {
		this.wjzf = wjzf;
	}

	public StglModel[] getStModel() {
		return stModel;
	}

	public void setStModel(StglModel[] stModel) {
		this.stModel = stModel;
	}

	public String getDjrid() {
		return djrid;
	}

	public void setDjrid(String djrid) {
		this.djrid = djrid;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getDags() {
		return dags;
	}

	public void setDags(String dags) {
		this.dags = dags;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public String getGqsj() {
		return gqsj;
	}

	public void setGqsj(String gqsj) {
		this.gqsj = gqsj;
	}

	public String getWjlxmc() {
		return wjlxmc;
	}

	public void setWjlxmc(String wjlxmc) {
		this.wjlxmc = wjlxmc;
	}

	public String getDjzt() {
		return djzt;
	}

	public void setDjzt(String djzt) {
		this.djzt = djzt;
	}

	public final String getAutoaddstbh() {
		return autoaddstbh;
	}

	public final void setAutoaddstbh(String autoaddstbh) {
		this.autoaddstbh = autoaddstbh;
	}

	public final String[] getYsgndms() {
		return ysgndms;
	}

	public final void setYsgndms(String[] ysgndms) {
		this.ysgndms = ysgndms;
	}

	public String getCjrmc() {
		return cjrmc;
	}

	public void setCjrmc(String cjrmc) {
		this.cjrmc = cjrmc;
	}

	/**
	 * 返回
	 */
	public String getModelId() {
		return modelId;
	}

	/**
	 * 设置
	 * @param modelId 
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

}
