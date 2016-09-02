package com.zfsoft.evaluation.entity;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 
 * @author Administrator
 *
 */
public class OpenLessonSettingEntity extends BaseQuery {
    /**
     * 
     */
    private static final long serialVersionUID = -543534452562367455L;
    // 听课申请ID
    private String tksqid;
    // 听课申请批次名称
    private String tksqpcmc;
    // 使用状态（0启用2停用1失效）
    private String syzt;
    // 申请时间开始
    private String sqsjks;
    // 申请时间结束
    private String sqsjjs;
    // 课程时间开始
    private String kcsjks;
    // 课程时间结束
    private String kcsjjs;
    
    /**
     * @return the tksqid
     */
    public String getTksqid() {
        return tksqid;
    }

    /**
     * @param tksqid the tksqid to set
     */
    public void setTksqid(String tksqid) {
        this.tksqid = tksqid;
    }

    /**
     * @return the tksqpcmc
     */
    public String getTksqpcmc() {
        return tksqpcmc;
    }

    /**
     * @param tksqpcmc the tksqpcmc to set
     */
    public void setTksqpcmc(String tksqpcmc) {
        this.tksqpcmc = tksqpcmc;
    }

    /**
     * @return the syzt
     */
    public String getSyzt() {
        return syzt;
    }

    /**
     * @param syzt the syzt to set
     */
    public void setSyzt(String syzt) {
        this.syzt = syzt;
    }

    /**
     * @return the sqsjks
     */
    public String getSqsjks() {
        return sqsjks;
    }

    /**
     * @param sqsjks the sqsjks to set
     */
    public void setSqsjks(String sqsjks) {
        this.sqsjks = sqsjks;
    }

    /**
     * @return the sqsjjs
     */
    public String getSqsjjs() {
        return sqsjjs;
    }

    /**
     * @param sqsjjs the sqsjjs to set
     */
    public void setSqsjjs(String sqsjjs) {
        this.sqsjjs = sqsjjs;
    }

    /**
     * @return the kcsjks
     */
    public String getKcsjks() {
        return kcsjks;
    }

    /**
     * @param kcsjks the kcsjks to set
     */
    public void setKcsjks(String kcsjks) {
        this.kcsjks = kcsjks;
    }

    /**
     * @return the kcsjjs
     */
    public String getKcsjjs() {
        return kcsjjs;
    }

    /**
     * @param kcsjjs the kcsjjs to set
     */
    public void setKcsjjs(String kcsjjs) {
        this.kcsjjs = kcsjjs;
    }
    
}
