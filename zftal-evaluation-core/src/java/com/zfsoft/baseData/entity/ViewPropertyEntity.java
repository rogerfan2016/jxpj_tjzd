package com.zfsoft.baseData.entity;

/**
 * 
 * @author Administrator
 *
 */
public class ViewPropertyEntity {
    // 信息类ID
    private String classId;
    // 属性ID
    private String propertyId;
    // 显示状态
    private String viewStatus;
    // 条件状态
    private String conditionStatus;

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
     * @return the propertyId
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * @param propertyId the propertyId to set
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * @return the viewStatus
     */
    public String getViewStatus() {
        return viewStatus;
    }

    /**
     * @param viewStatus the viewStatus to set
     */
    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }

    /**
     * @return the conditionStatus
     */
    public String getConditionStatus() {
        return conditionStatus;
    }

    /**
     * @param conditionStatus the conditionStatus to set
     */
    public void setConditionStatus(String conditionStatus) {
        this.conditionStatus = conditionStatus;
    }
    
}
