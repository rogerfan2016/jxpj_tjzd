package com.zfsoft.evaluation.entity;

/**
 * 
 * @author Administrator
 *
 */
public class SettingEntity {
    // 全局ID
    private String globalid;
    // 问卷ID
    private String wjid;
    // 问卷名称
    private String wjmc;
    // 答卷人员类型
    private String djrylx;
    // 使用状态
    private String syzt;
    // 作成时间
    private String zcsj;
    // 作成人员(工号)
    private String zcry;
    // 作成人员(姓名)
    private String zcryxm;
    // 修改时间
    private String xgsj;
    // 修改人员(工号)
    private String xgry;
    // 修改人员(姓名)
    private String xgryxm;
    // 评教类型
    private EvaluationType pjlx;
    
    /**
     * @return the djrylx
     */
    public String getDjrylx() {
        return djrylx;
    }
    
    /**
     * @param djrylx the djrylx to set
     */
    public void setDjrylx(String djrylx) {
        this.djrylx = djrylx;
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
     * @return the zcsj
     */
    public String getZcsj() {
        return zcsj;
    }

    /**
     * @param zcsj the zcsj to set
     */
    public void setZcsj(String zcsj) {
        this.zcsj = zcsj;
    }

    /**
     * @return the zcry
     */
    public String getZcry() {
        return zcry;
    }

    /**
     * @param zcry the zcry to set
     */
    public void setZcry(String zcry) {
        this.zcry = zcry;
    }

    /**
     * @return the zcryxm
     */
    public String getZcryxm() {
        return zcryxm;
    }

    /**
     * @param zcryxm the zcryxm to set
     */
    public void setZcryxm(String zcryxm) {
        this.zcryxm = zcryxm;
    }

    /**
     * @return the xgsj
     */
    public String getXgsj() {
        return xgsj;
    }

    /**
     * @param xgsj the xgsj to set
     */
    public void setXgsj(String xgsj) {
        this.xgsj = xgsj;
    }

    /**
     * @return the xgry
     */
    public String getXgry() {
        return xgry;
    }

    /**
     * @param xgry the xgry to set
     */
    public void setXgry(String xgry) {
        this.xgry = xgry;
    }

    /**
     * @return the xgryxm
     */
    public String getXgryxm() {
        return xgryxm;
    }

    /**
     * @param xgryxm the xgryxm to set
     */
    public void setXgryxm(String xgryxm) {
        this.xgryxm = xgryxm;
    }

    /**
     * @return the pjlx
     */
    public EvaluationType getPjlx() {
        return pjlx;
    }

    /**
     * @param pjlx the pjlx to set
     */
    public void setPjlx(EvaluationType pjlx) {
        this.pjlx = pjlx;
    }

}
