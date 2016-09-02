package com.zfsoft.monitor.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.hrm.baseinfo.dyna.entities.DynaBean;
import com.zfsoft.hrm.baseinfo.dyna.util.DynaBeanUtil;
import com.zfsoft.util.base.StringUtil;

/**
 * 
 * @author Administrator
 *
 */
public class PatrolEntity {
    // 全局ID
    private String globalid;
    // 巡视类型（dept学院school学校）
    private PatrolType xslx;
    // 巡视单位
    private String xsdw;
    // 巡视日期
    private String xsrq;
    // 教学周
    private String jxz ;
    // 巡视人员
    private String xsry;
    // 巡视人员姓名
    private String xsryxm;
    // 巡视场地代码
    private String xscddm;
    // 巡视场地名称
    private String xscdmc;
    // 课程节次
    private String kcjc;
    // 星期
    private String weekOfDay;
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
    // 状态（0删除；1正常）
    private String zt;
    
    // 巡视记录集合
    private List<PatrolDetailEntity> patrolDetailEntityList;
    // 是否可巡视
    private String sfkxs;
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
     * @return the xslx
     */
    public PatrolType getXslx() {
        return xslx;
    }

    /**
     * @param xslx the xslx to set
     */
    public void setXslx(PatrolType xslx) {
        this.xslx = xslx;
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
        if(StringUtil.isNotEmpty(xsry)){
        	String[] yhms = xsry.split(";");
            DynaBean selfInfo;
            String xms = "";
            for (String yhm : yhms) {
                selfInfo = DynaBeanUtil.getPerson(yhm.trim());
                if(selfInfo != null){
                	if (StringUtils.isEmpty(xms)) {
                        xms = selfInfo.getValueString("xm");
                    } else {
                        xms += ";" + selfInfo.getValueString("xm");
                    }
                }                
            }
            this.setXsryxm(xms);
        }       
    }

    /**
	 * @return the xscddm
	 */
	public String getXscddm() {
		return xscddm;
	}

	/**
	 * @param xscddm the xscddm to set
	 */
	public void setXscddm(String xscddm) {
		this.xscddm = xscddm;
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
     * @return the weekOfDay
     */
    public String getWeekOfDay() {
        return weekOfDay;
    }

    /**
     * @param weekOfDay the weekOfDay to set
     */
    public void setWeekOfDay(String weekOfDay) {
        this.weekOfDay = weekOfDay;
    }

    /**
     * @return the xsryxm
     */
    public String getXsryxm() {
        return xsryxm;
    }

    /**
     * @param xsryxm the xsryxm to set
     */
    public void setXsryxm(String xsryxm) {
        this.xsryxm = xsryxm;
    }

	/**
	 * @return the patrolDetailEntityList
	 */
	public List<PatrolDetailEntity> getPatrolDetailEntityList() {
		return patrolDetailEntityList;
	}

	/**
	 * @param patrolDetailEntityList the patrolDetailEntityList to set
	 */
	public void setPatrolDetailEntityList(
			List<PatrolDetailEntity> patrolDetailEntityList) {
		this.patrolDetailEntityList = patrolDetailEntityList;
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

	public String getSfkxs() {
		return sfkxs;
	}

	public void setSfkxs(String sfkxs) {
		this.sfkxs = sfkxs;
	}

}
