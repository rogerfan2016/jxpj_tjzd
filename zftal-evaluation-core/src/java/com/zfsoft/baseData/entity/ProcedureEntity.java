package com.zfsoft.baseData.entity;


/**
 * 
 * @author Administrator
 *
 */
public class ProcedureEntity {
    // 信息类ID
    private String classId;
    // 信息类名称
    private String className;
    // 存储过程名
    private String procedureName;
    // 存储过程ID
    private String procedureId;
    // 执行周期（0每月1每季度2每半年3每年）
    private String executeCyc;
    // 定时开关（on开off关）
    private String regularSwitch;

    /**
     * @return the classId
     */
    public String getClassId() {
        return classId;
    }

    /**
     * @param classId the classId to set
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * @return the procedureName
     */
    public String getProcedureName() {
        return procedureName;
    }

    /**
     * @param procedureName the procedureName to set
     */
    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the procedureId
     */
    public String getProcedureId() {
        return procedureId;
    }

    /**
     * @param procedureId the procedureId to set
     */
    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    /**
     * @return the executeCyc
     */
    public String getExecuteCyc() {
        return executeCyc;
    }

    /**
     * @param executeCyc the executeCyc to set
     */
    public void setExecuteCyc(String executeCyc) {
        this.executeCyc = executeCyc;
    }

    /**
     * @return the regularSwitch
     */
    public String getRegularSwitch() {
        return regularSwitch;
    }

    /**
     * @param regularSwitch the regularSwitch to set
     */
    public void setRegularSwitch(String regularSwitch) {
        this.regularSwitch = regularSwitch;
    }
    
}
