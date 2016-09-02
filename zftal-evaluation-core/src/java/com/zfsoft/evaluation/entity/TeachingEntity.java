package com.zfsoft.evaluation.entity;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class TeachingEntity {
    // 全局ID
    private String globalid;
    // 课程ID
    private String kcid;
    // 课程名称
    private String kcmc;
    // 课程时间
    private String kcsj;
    // 任课老师工号
    private String rklsgh;
    // 任课老师
    private String rkls;
    // 教学内容
    private String jxnr;
    // 课外作业
    private String kwzy;
    // 备注说明
    private String bzsm;
    // 班级ID
    private String bjid;
    // 学生
    private List<CheckInEntity> xss;
    // 课程节次
    private String kcjc;
    // 上课地点
    private String skdd;
    // 状态
    private String zt;

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
     * @return the kcid
     */
    public String getKcid() {
        return kcid;
    }

    /**
     * @param kcid the kcid to set
     */
    public void setKcid(String kcid) {
        this.kcid = kcid;
    }

    /**
     * @return the kcmc
     */
    public String getKcmc() {
        return kcmc;
    }

    /**
     * @param kcmc the kcmc to set
     */
    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    /**
     * @return the kcsj
     */
    public String getKcsj() {
        return kcsj;
    }

    /**
     * @param kcsj the kcsj to set
     */
    public void setKcsj(String kcsj) {
        this.kcsj = kcsj;
    }

    /**
     * @return the rklsgh
     */
    public String getRklsgh() {
        return rklsgh;
    }

    /**
     * @param rklsgh the rklsgh to set
     */
    public void setRklsgh(String rklsgh) {
        this.rklsgh = rklsgh;
    }

    /**
     * @return the rkls
     */
    public String getRkls() {
        return rkls;
    }

    /**
     * @param rkls the rkls to set
     */
    public void setRkls(String rkls) {
        this.rkls = rkls;
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
     * @return the kwzy
     */
    public String getKwzy() {
        return kwzy;
    }

    /**
     * @param kwzy the kwzy to set
     */
    public void setKwzy(String kwzy) {
        this.kwzy = kwzy;
    }

    /**
     * @return the bzsm
     */
    public String getBzsm() {
        return bzsm;
    }

    /**
     * @param bzsm the bzsm to set
     */
    public void setBzsm(String bzsm) {
        this.bzsm = bzsm;
    }

    /**
     * @return the bjid
     */
    public String getBjid() {
        return bjid;
    }

    /**
     * @param bjid the bjid to set
     */
    public void setBjid(String bjid) {
        this.bjid = bjid;
    }

    /**
     * @return the xss
     */
    public List<CheckInEntity> getXss() {
        return xss;
    }

    /**
     * @param xss the xss to set
     */
    public void setXss(List<CheckInEntity> xss) {
        this.xss = xss;
    }

    /**
     * @return the kcjc
     */
    public String getKcjc() {
        return kcjc;
    }

    /**
     * @param kcjc the kcjc to set
     */
    public void setKcjc(String kcjc) {
        this.kcjc = kcjc;
    }

    /**
     * @return the skdd
     */
    public String getSkdd() {
        return skdd;
    }

    /**
     * @param skdd the skdd to set
     */
    public void setSkdd(String skdd) {
        this.skdd = skdd;
    }

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

}
