package com.zfsoft.monitor.entity;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 
 * @author Administrator
 *
 */
public class PatrolQuery extends BaseQuery {

    /**
     * 
     */
    private static final long serialVersionUID = 2279444480158617039L;
    // 巡视任务ID
    private String globalid;
    // 巡视日期
    private String xsrq;
    // 教学周
    private String jxz;
    // 巡视人员
    private String xsry;
    // 巡视地点
    private String xscdmc;
    // 巡视单位
    private String xsdw;
    // 巡视类型
    private String xslx;
    // 课程节次
    private String kcjc;
    // 状态
    private String zt;
    // 处理类型
    private String cllx;
    // 条件
    private String condition;
    // 教学楼
    private String jxl;
//===========================================    
    private String firstDay;
    
    private String lastDay;

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
     * @return the xsdw
     */
    public String getXsdw() {
        return xsdw;
    }

    /**
     * @param xsdw the xsdw to set
     */
    public void setXsdw(String xsdw) {
        this.xsdw = xsdw;
    }

    /**
     * @return the xsrq
     */
    public String getXsrq() {
        return xsrq;
    }

    /**
     * @param xsrq the xsrq to set
     */
    public void setXsrq(String xsrq) {
        this.xsrq = xsrq;
    }

    /**
     * @return the jxz
     */
    public String getJxz() {
        return jxz;
    }

    /**
     * @param jxz the jxz to set
     */
    public void setJxz(String jxz) {
        this.jxz = jxz;
    }

    /**
     * @return the xsry
     */
    public String getXsry() {
        return xsry;
    }

    /**
     * @param xsry the xsry to set
     */
    public void setXsry(String xsry) {
        this.xsry = xsry;
    }

    /**
	 * @return the xscdmc
	 */
	public String getXscdmc() {
		return xscdmc;
	}

	/**
	 * @param xscdmc the xscdmc to set
	 */
	public void setXscdmc(String xscdmc) {
		this.xscdmc = xscdmc;
	}

	/**
     * @return the xslx
     */
    public String getXslx() {
        return xslx;
    }

    /**
     * @param xslx the xslx to set
     */
    public void setXslx(String xslx) {
        this.xslx = xslx;
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
	 * @return the zt
	 */
	public String getZt() {
		return zt;
	}

	/**
	 * @param zt the zt to set
	 */
	public void setZt(String zt) {
		this.zt = zt;
	}

	/**
	 * @return the jxl
	 */
	public String getJxl() {
		return jxl;
	}

	/**
	 * @param jxl the jxl to set
	 */
	public void setJxl(String jxl) {
		this.jxl = jxl;
	}

	/**
	 * @return the cllx
	 */
	public String getCllx() {
		return cllx;
	}

	/**
	 * @param cllx the cllx to set
	 */
	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}

	public String getLastDay() {
		return lastDay;
	}

	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}

}
