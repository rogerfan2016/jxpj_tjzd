package com.zfsoft.evaluation.entity;

/**
 * 
 * @author Administrator
 *
 */
public class EvaluationConnEntity {
    // 问卷ID
    private String wjid;
    // 新问卷ID
    private String xwjid;
    // 评教状态（0未评价1已评价）
    private String pjzt;
    // 评教时间
    private String pjsj;
    // ID
    private String globalid;
    // 表
    private String tableSql;
    // 条件
    private String condition;
    // 评教人员类型
    private String pjrylx;
    
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
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
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
     * @return the pjrylx
     */
    public String getPjrylx() {
        return pjrylx;
    }

    /**
     * @param pjrylx the pjrylx to set
     */
    public void setPjrylx(String pjrylx) {
        this.pjrylx = pjrylx;
    }

}
