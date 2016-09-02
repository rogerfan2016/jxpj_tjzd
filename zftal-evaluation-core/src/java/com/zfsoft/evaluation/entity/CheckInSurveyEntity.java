package com.zfsoft.evaluation.entity;

/**
 * 
 * @author Administrator
 *
 */
public class CheckInSurveyEntity {
    
    private CurriculumScheduleEntity curriculum;
    private CheckInEntity checkInEntity;
    // 星期
    private String dayofweek;
    // 应出勤人数
    private int ycqrs;
    // 实际出勤人数
    private int sjcqrs;
    // 缺勤人数
    private int qqrs;
    // 是否发起评教
    private String sffqpj;
    // 评教参与人数
    private int pjcyrs;
    // 年度
    private String schoolYear;
    // 学期
    private String semester;
    // 部门
    private String dept;
    // 教师姓名
    private String jsxm;
    //============================
    // 学生ID
    private String xsid;
    // 学生姓名
    private String xsxm;
    // 课程ID
    private String kcid;
    // 课程名称
    private String kcmc;
    // 学院
    private String xy;
    // 专业
    private String zy;
    // 年级
    private String nj;
    // 行政班
    private String xzb;
    // 考勤次数
    private String kqcs;
    // 出勤次数
    private String cqcs;
    // 旷课次数
    private String kkcs;
    // 事假次数
    private String sjcs;
    // 病假次数
    private String bjcs;
    // 迟到次数
    private String cdcs;
    // 出勤比例
    private String cqbl;
    // 缺勤比例
    private String qqbl;
    // 缺勤学生
    private String qqxs;
    // 课程总学时
    private String kczxs;

    /**
     * @return the curriculum
     */
    public CurriculumScheduleEntity getCurriculum() {
        return curriculum;
    }

    /**
     * @param curriculum the curriculum to set
     */
    public void setCurriculum(CurriculumScheduleEntity curriculum) {
        this.curriculum = curriculum;
    }

    /**
     * @return the ycqrs
     */
    public int getYcqrs() {
        return ycqrs;
    }

    /**
     * @param ycqrs the ycqrs to set
     */
    public void setYcqrs(int ycqrs) {
        this.ycqrs = ycqrs;
    }

    /**
     * @return the sjcqrs
     */
    public int getSjcqrs() {
        return sjcqrs;
    }

    /**
     * @param sjcqrs the sjcqrs to set
     */
    public void setSjcqrs(int sjcqrs) {
        this.sjcqrs = sjcqrs;
    }

    /**
     * @return the qqrs
     */
    public int getQqrs() {
        return qqrs;
    }

    /**
     * @param qqrs the qqrs to set
     */
    public void setQqrs(int qqrs) {
        this.qqrs = qqrs;
    }

    /**
     * @return the sffqpj
     */
    public String getSffqpj() {
        return sffqpj;
    }

    /**
     * @param sffqpj the sffqpj to set
     */
    public void setSffqpj(String sffqpj) {
        this.sffqpj = sffqpj;
    }

    /**
     * @return the pjcyrs
     */
    public int getPjcyrs() {
        return pjcyrs;
    }

    /**
     * @param pjcyrs the pjcyrs to set
     */
    public void setPjcyrs(int pjcyrs) {
        this.pjcyrs = pjcyrs;
    }

    /**
     * @return the dayofweek
     */
    public String getDayofweek() {
        return dayofweek;
    }

    /**
     * @param dayofweek the dayofweek to set
     */
    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    /**
     * @return the schoolYear
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * @param schoolYear the schoolYear to set
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
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
     * @return the checkInEntity
     */
    public CheckInEntity getCheckInEntity() {
        return checkInEntity;
    }

    /**
     * @param checkInEntity the checkInEntity to set
     */
    public void setCheckInEntity(CheckInEntity checkInEntity) {
        this.checkInEntity = checkInEntity;
    }

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	/**
	 * @return the kqcs
	 */
	public String getKqcs() {
		return kqcs;
	}

	/**
	 * @param kqcs the kqcs to set
	 */
	public void setKqcs(String kqcs) {
		this.kqcs = kqcs;
	}

	/**
	 * @return the cqcs
	 */
	public String getCqcs() {
		return cqcs;
	}

	/**
	 * @param cqcs the cqcs to set
	 */
	public void setCqcs(String cqcs) {
		this.cqcs = cqcs;
	}

	/**
	 * @return the kkcs
	 */
	public String getKkcs() {
		return kkcs;
	}

	/**
	 * @param kkcs the kkcs to set
	 */
	public void setKkcs(String kkcs) {
		this.kkcs = kkcs;
	}

	/**
	 * @return the sjcs
	 */
	public String getSjcs() {
		return sjcs;
	}

	/**
	 * @param sjcs the sjcs to set
	 */
	public void setSjcs(String sjcs) {
		this.sjcs = sjcs;
	}

	/**
	 * @return the bjcs
	 */
	public String getBjcs() {
		return bjcs;
	}

	/**
	 * @param bjcs the bjcs to set
	 */
	public void setBjcs(String bjcs) {
		this.bjcs = bjcs;
	}

	public String getCdcs() {
		return cdcs;
	}

	public void setCdcs(String cdcs) {
		this.cdcs = cdcs;
	}

	/**
	 * @return the qqbl
	 */
	public String getQqbl() {
		return qqbl;
	}

	/**
	 * @param qqbl the qqbl to set
	 */
	public void setQqbl(String qqbl) {
		this.qqbl = qqbl;
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
	 * @return the xy
	 */
	public String getXy() {
		return xy;
	}

	/**
	 * @param xy the xy to set
	 */
	public void setXy(String xy) {
		this.xy = xy;
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
	 * @return the cqbl
	 */
	public String getCqbl() {
		return cqbl;
	}

	/**
	 * @param cqbl the cqbl to set
	 */
	public void setCqbl(String cqbl) {
		this.cqbl = cqbl;
	}

	public String getKcid() {
		return kcid;
	}

	public void setKcid(String kcid) {
		this.kcid = kcid;
	}

	public String getKcmc() {
		return kcmc;
	}

	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getQqxs() {
		return qqxs;
	}

	public void setQqxs(String qqxs) {
		this.qqxs = qqxs;
	}

	public String getJsxm() {
		return jsxm;
	}

	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}

	public String getKczxs() {
		return kczxs;
	}

	public void setKczxs(String kczxs) {
		this.kczxs = kczxs;
	}
    
}
