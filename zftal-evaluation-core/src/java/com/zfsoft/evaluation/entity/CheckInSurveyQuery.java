package com.zfsoft.evaluation.entity;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 
 * @author Administrator
 *
 */
public class CheckInSurveyQuery extends BaseQuery {

    /**
     * 
     */
    private static final long serialVersionUID = 2068036902468701250L;
    // 年份
    private String year;
    // 年度
    private String schoolyear;
    // 学期
    private String semester;
    // 课程时间From
    private String kcsjFrom;
    // 课程时间To
    private String kcsjTo;
    // 工号
    private String userid;
    // 类型
    private String userlx;
    // 开始日
    private String firstDay;
    // 结束日
    private String lastDay;
    // 工号
    private String gh;
    // 姓名
    private String xm;
    // 部门
    private String dept;
    // 学院
    private String xy;
    // 专业
    private String zy;
    // 条件
    private String condition;
    // 评教情况
    private String pjqk;
    // 是否查看自己
    private String sfckzj;
    // 课程名称
    private String kcmc;
    // 行政班
    private String xzb;
    // 年级
    private String nj;
    // 学生ID
    private String xsid;
    // 学生姓名
    private String xsxm;
    // 月份
    private String month;
    // 全局ID
    private String globalid;
    // 教师姓名
    private String jsxm;
    // 课程节次
    private String kcjc;
    
    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
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
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the userlx
     */
    public String getUserlx() {
        return userlx;
    }

    /**
     * @param userlx the userlx to set
     */
    public void setUserlx(String userlx) {
        this.userlx = userlx;
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
     * @return the gh
     */
    public String getGh() {
        return gh;
    }

    /**
     * @param gh the gh to set
     */
    public void setGh(String gh) {
        this.gh = gh;
    }

    /**
     * @return the xm
     */
    public String getXm() {
        return xm;
    }

    /**
     * @param xm the xm to set
     */
    public void setXm(String xm) {
        this.xm = xm;
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
     * @return the pjqk
     */
    public String getPjqk() {
        return pjqk;
    }

    /**
     * @param pjqk the pjqk to set
     */
    public void setPjqk(String pjqk) {
        this.pjqk = pjqk;
    }

    /**
     * @return the sfckzj
     */
    public String getSfckzj() {
        return sfckzj;
    }

    /**
     * @param sfckzj the sfckzj to set
     */
    public void setSfckzj(String sfckzj) {
        this.sfckzj = sfckzj;
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

	public String getGlobalid() {
		return globalid;
	}

	public void setGlobalid(String globalid) {
		this.globalid = globalid;
	}

	/**
	 * @return the xzb
	 */
	public String getXzb() {
		return xzb;
	}

	/**
	 * @param xzb the xzb to set
	 */
	public void setXzb(String xzb) {
		this.xzb = xzb;
	}

	/**
	 * @return the xsid
	 */
	public String getXsid() {
		return xsid;
	}

	/**
	 * @param xsid the xsid to set
	 */
	public void setXsid(String xsid) {
		this.xsid = xsid;
	}

	/**
	 * @return the xsxm
	 */
	public String getXsxm() {
		return xsxm;
	}

	/**
	 * @param xsxm the xsxm to set
	 */
	public void setXsxm(String xsxm) {
		this.xsxm = xsxm;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getJsxm() {
		return jsxm;
	}

	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}

	public String getKcjc() {
		return kcjc;
	}

	public void setKcjc(String kcjc) {
		this.kcjc = kcjc;
	}
    
}
