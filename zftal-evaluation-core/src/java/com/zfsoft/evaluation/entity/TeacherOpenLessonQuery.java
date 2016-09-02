package com.zfsoft.evaluation.entity;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 
 * @author Administrator
 *
 */
public class TeacherOpenLessonQuery extends BaseQuery {

    /**
     * 
     */
    private static final long serialVersionUID = 4222227039211299905L;
    // 听课月份
    private String tkyf;
    // 听课教师ID
    private String tkjsid;
    // 状态
    private String shzt;
    // 审核阶段
    private String phase;
    // 听课老师姓名
    private String tkjsxm;
    // 专业
    private String zy;
    // 课程名称
    private String kcmc;
    // 听课地点
    private String tkdd;
    // 听课时间(From)
    private String kcsjFrom;
    // 听课时间(To)
    private String kcsjTo;
    // 条件
    private String condition;
    // 年度
    private String schoolyear;
    // 学期
    private String semester;
    // 部门
    private String dept;
    // 开始日
    private String firstDay;
    // 结束日
    private String lastDay;
    // 查询表
    private String tableSql;
    // 是否评价
    private String sfpj;

    /**
     * @return the tkyf
     */
    public String getTkyf() {
        return tkyf;
    }
    
    /**
     * @param tkyf the tkyf to set
     */
    public void setTkyf(String tkyf) {
        this.tkyf = tkyf;
    }

    /**
     * @return the tkjsid
     */
    public String getTkjsid() {
        return tkjsid;
    }

    /**
     * @param tkjsid the tkjsid to set
     */
    public void setTkjsid(String tkjsid) {
        this.tkjsid = tkjsid;
    }

    /**
     * @return the shzt
     */
    public String getShzt() {
        return shzt;
    }

    /**
     * @param shzt the shzt to set
     */
    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    /**
     * @return the phase
     */
    public String getPhase() {
        return phase;
    }

    /**
     * @param phase the phase to set
     */
    public void setPhase(String phase) {
        this.phase = phase;
    }

    /**
     * @return the tkjsxm
     */
    public String getTkjsxm() {
        return tkjsxm;
    }

    /**
     * @param tkjsxm the tkjsxm to set
     */
    public void setTkjsxm(String tkjsxm) {
        this.tkjsxm = tkjsxm;
    }

    /**
     * @return the zy
     */
    public String getZy() {
        return zy;
    }

    /**
     * @param zy the zy to set
     */
    public void setZy(String zy) {
        this.zy = zy;
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
     * @return the tkdd
     */
    public String getTkdd() {
        return tkdd;
    }

    /**
     * @param tkdd the tkdd to set
     */
    public void setTkdd(String tkdd) {
        this.tkdd = tkdd;
    }

    /**
     * @return the kcsjFrom
     */
    public String getKcsjFrom() {
        return kcsjFrom;
    }

    /**
     * @param kcsjFrom the kcsjFrom to set
     */
    public void setKcsjFrom(String kcsjFrom) {
        this.kcsjFrom = kcsjFrom;
    }

    /**
     * @return the kcsjTo
     */
    public String getKcsjTo() {
        return kcsjTo;
    }

    /**
     * @param kcsjTo the kcsjTo to set
     */
    public void setKcsjTo(String kcsjTo) {
        this.kcsjTo = kcsjTo;
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
     * @return the schoolyear
     */
    public String getSchoolyear() {
        return schoolyear;
    }

    /**
     * @param schoolyear the schoolyear to set
     */
    public void setSchoolyear(String schoolyear) {
        this.schoolyear = schoolyear;
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * @return the dept
     */
    public String getDept() {
        return dept;
    }

    /**
     * @param dept the dept to set
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * @return the firstDay
     */
    public String getFirstDay() {
        return firstDay;
    }

    /**
     * @param firstDay the firstDay to set
     */
    public void setFirstDay(String firstDay) {
        this.firstDay = firstDay;
    }

    /**
     * @return the lastDay
     */
    public String getLastDay() {
        return lastDay;
    }

    /**
     * @param lastDay the lastDay to set
     */
    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
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
     * @return the sfpj
     */
    public String getSfpj() {
        return sfpj;
    }

    /**
     * @param sfpj the sfpj to set
     */
    public void setSfpj(String sfpj) {
        this.sfpj = sfpj;
    }
    
}
