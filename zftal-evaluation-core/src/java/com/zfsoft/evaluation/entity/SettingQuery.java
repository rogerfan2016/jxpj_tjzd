package com.zfsoft.evaluation.entity;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 
 * @author Administrator
 *
 */
public class SettingQuery extends BaseQuery {

    /**
     * 
     */
    private static final long serialVersionUID = 3074334684270287593L;
    // 问卷名称
    private String wjmc;
    // 答卷人员类型
    private String djrylx;
    // 使用状态
    private String syzt;
    // 评教类型
    private String pjlx;
    
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
     * @return the pjlx
     */
    public String getPjlx() {
        return pjlx;
    }

    /**
     * @param pjlx the pjlx to set
     */
    public void setPjlx(String pjlx) {
        this.pjlx = pjlx;
    }

}
