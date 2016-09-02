package com.zfsoft.evaluation.entity;

import java.util.Map;

/**
 * 
 * @author Administrator
 *
 */
public class ViewQuestionnaireEntity {
    // 问卷ID
    private String wjid;
    // 新问卷ID
    private String xwjid;
    // 问卷名称
    private String wjmc;
    // 评教状态（0未评价1已评价）
    private String pjzt;
    // 评教时间
    private String pjsj;
    // 评教人员
    private String pjryid;
    // 评教ID
    private String pjid;
    // 主键值
    private String zjz;
    // 表
    private String tableSql;
    // 字段
    private String pjryfiled;
    // 课程信息
    private CurriculumScheduleEntity curriculum;
    // 全局ID
    private String globalid;
    // 评价人数
    private Map<String, Object> pjcnt;

    /**
     * @return the wjid
     */
    public String getWjid() {
        return wjid;
    }
    
    /**
     * @param wjid the wjid to set
     */
    public void setWjid(String wjid) {
        this.wjid = wjid;
    }
    
    /**
     * @return the xwjid
     */
    public String getXwjid() {
        return xwjid;
    }
    
    /**
     * @param xwjid the xwjid to set
     */
    public void setXwjid(String xwjid) {
        this.xwjid = xwjid;
    }

    /**
     * @return the tableSql
     */
    public String getTableSql() {
        return tableSql;
    }

    /**
     * @param tableSql the tableSql to set
     */
    public void setTableSql(String tableSql) {
        this.tableSql = tableSql;
    }

    /**
     * @return the pjzt
     */
    public String getPjzt() {
        return pjzt;
    }

    /**
     * @param pjzt the pjzt to set
     */
    public void setPjzt(String pjzt) {
        this.pjzt = pjzt;
    }

    /**
     * @return the pjsj
     */
    public String getPjsj() {
        return pjsj;
    }

    /**
     * @param pjsj the pjsj to set
     */
    public void setPjsj(String pjsj) {
        this.pjsj = pjsj;
    }

    /**
     * @return the pjryid
     */
    public String getPjryid() {
        return pjryid;
    }

    /**
     * @param pjryid the pjryid to set
     */
    public void setPjryid(String pjryid) {
        this.pjryid = pjryid;
    }

    /**
     * @return the pjid
     */
    public String getPjid() {
        return pjid;
    }

    /**
     * @param pjid the pjid to set
     */
    public void setPjid(String pjid) {
        this.pjid = pjid;
    }

    /**
     * @return the pjryfiled
     */
    public String getPjryfiled() {
        return pjryfiled;
    }

    public String getZjz() {
		return zjz;
	}

	public void setZjz(String zjz) {
		this.zjz = zjz;
	}

	/**
     * @param pjryfiled the pjryfiled to set
     */
    public void setPjryfiled(String pjryfiled) {
        this.pjryfiled = pjryfiled;
    }

    /**
     * @return the wjmc
     */
    public String getWjmc() {
        return wjmc;
    }

    /**
     * @param wjmc the wjmc to set
     */
    public void setWjmc(String wjmc) {
        this.wjmc = wjmc;
    }

    /**
     * @return the curriculum
     */
    public CurriculumScheduleEntity getCurriculum() {
        return curriculum;
    }

    /**
     * @param curriculum the curriculum to set
     */
    public void setCurriculum(CurriculumScheduleEntity curriculum) {
        this.curriculum = curriculum;
    }

    /**
     * @return the globalid
     */
    public String getGlobalid() {
        return globalid;
    }

    /**
     * @param globalid the globalid to set
     */
    public void setGlobalid(String globalid) {
        this.globalid = globalid;
    }

    /**
     * @return the pjcnt
     */
    public Map<String, Object> getPjcnt() {
        return pjcnt;
    }

    /**
     * @param pjcnt the pjcnt to set
     */
    public void setPjcnt(Map<String, Object> pjcnt) {
        this.pjcnt = pjcnt;
    }

}
