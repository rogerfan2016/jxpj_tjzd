package com.zfsoft.monitor.entity;

import java.util.Date;

import com.zfsoft.hrm.baseinfo.dyna.entities.DynaBean;
import com.zfsoft.hrm.baseinfo.dyna.util.DynaBeanUtil;
import com.zfsoft.util.base.StringUtil;

/**
 * 
 * @author Administrator
 *
 */
public class PatrolDetailEntity {
	// 巡视记录ID
	private String id;
    // 巡视任务ID
    private String globalid;
    // 巡视人员
    private String xsry;
    // 巡视人员姓名
    private String xsryxm;
    // 巡视地点代码
    private String dddm;
    // 巡视地点(教室)
    private String xsdd;
    // 上课学生数
    private String skxss;
    // 课程名称
    private String kcmc;
    // 课程代码
    private String kcdm;
    // 教师工号
    private String jsgh;
    // 教师姓名
    private String jsxm;
    // 上报时间
    private Date sbsj;
    // 状态
    private String zt;
    // 总体情况
    private String ztqk;
    // 存在问题
    private String czwt;
    // 处理结果
    private String cljg;
    // 备注
    private String bz;
    // 院系处理意见
    private String yxclyj;
    // 院系处理人
    private String yxclr;
    // 院系处理时间
    private Date yxclsj;
    // 学校处理意见
    private String xxclyj;
    // 学校处理人
    private String xxclr;
    // 学校处理时间
    private Date xxclsj;
    // 教学楼
    private String jxl;
    // 开课学院
    private String kkxy;
    // 上课班级
    private String skbj;
    //==============================
    // 巡视类型（dept学院school学校）
    private String xslx;
    // 巡视日期
    private String xsrq;
    // 被巡视单位
    private String xsdw;
    // 巡视场地名称
    private String xscdmc;
    // 课程时间
    private String kcsj;
    // 课程节次
    private String kcjc;
    // 教学周
    private String jxz;
    // 星期
    private String weekOfDay;
    //===============================
    // 巡视任务数
    private String xsrws;
    // 巡视记录数 
    private String xsjls;
    // 巡视正常数 
    private String zcs;
    // 巡视存在问题数
    private String czwts;
    // 正常比例
    private String zcbl;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
            DynaBean selfInfo = DynaBeanUtil.getPerson(xsry);
            this.setXsryxm(selfInfo.getValueString("xm"));
        }       
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
	 * @return the ztqk
	 */
	public String getZtqk() {
		return ztqk;
	}
	/**
	 * @param ztqk the ztqk to set
	 */
	public void setZtqk(String ztqk) {
		this.ztqk = ztqk;
	}
	/**
	 * @return the czwt
	 */
	public String getCzwt() {
		return czwt;
	}
	/**
	 * @param czwt the czwt to set
	 */
	public void setCzwt(String czwt) {
		this.czwt = czwt;
	}
	/**
	 * @return the cljg
	 */
	public String getCljg() {
		return cljg;
	}
	/**
	 * @param cljg the cljg to set
	 */
	public void setCljg(String cljg) {
		this.cljg = cljg;
	}
	
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the sbsj
	 */
	public Date getSbsj() {
		return sbsj;
	}
	/**
	 * @param sbsj the sbsj to set
	 */
	public void setSbsj(Date sbsj) {
		this.sbsj = sbsj;
	}
	/**
	 * @return the xsdd
	 */
	public String getXsdd() {
		return xsdd;
	}
	/**
	 * @param xsdd the xsdd to set
	 */
	public void setXsdd(String xsdd) {
		this.xsdd = xsdd;
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
	 * @return the kcdm
	 */
	public String getKcdm() {
		return kcdm;
	}
	/**
	 * @param kcdm the kcdm to set
	 */
	public void setKcdm(String kcdm) {
		this.kcdm = kcdm;
	}
	/**
	 * @return the jsgh
	 */
	public String getJsgh() {
		return jsgh;
	}
	/**
	 * @param jsgh the jsgh to set
	 */
	public void setJsgh(String jsgh) {
		this.jsgh = jsgh;
	}
	/**
	 * @return the jsxm
	 */
	public String getJsxm() {
		return jsxm;
	}
	/**
	 * @param jsxm the jsxm to set
	 */
	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
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
	 * @return the dddm
	 */
	public String getDddm() {
		return dddm;
	}
	/**
	 * @param dddm the dddm to set
	 */
	public void setDddm(String dddm) {
		this.dddm = dddm;
	}
	/**
	 * @return the kcsj
	 */
	public String getKcsj() {
		return kcsj;
	}
	/**
	 * @param kcsj the kcsj to set
	 */
	public void setKcsj(String kcsj) {
		this.kcsj = kcsj;
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
	 * @return the skxss
	 */
	public String getSkxss() {
		return skxss;
	}
	/**
	 * @param skxss the skxss to set
	 */
	public void setSkxss(String skxss) {
		this.skxss = skxss;
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
	 * @return the yxclyj
	 */
	public String getYxclyj() {
		return yxclyj;
	}
	/**
	 * @param yxclyj the yxclyj to set
	 */
	public void setYxclyj(String yxclyj) {
		this.yxclyj = yxclyj;
	}
	/**
	 * @return the yxclr
	 */
	public String getYxclr() {
		return yxclr;
	}
	/**
	 * @param yxclr the yxclr to set
	 */
	public void setYxclr(String yxclr) {
		this.yxclr = yxclr;
	}
	/**
	 * @return the yxclsj
	 */
	public Date getYxclsj() {
		return yxclsj;
	}
	/**
	 * @param yxclsj the yxclsj to set
	 */
	public void setYxclsj(Date yxclsj) {
		this.yxclsj = yxclsj;
	}
	/**
	 * @return the xxclyj
	 */
	public String getXxclyj() {
		return xxclyj;
	}
	/**
	 * @param xxclyj the xxclyj to set
	 */
	public void setXxclyj(String xxclyj) {
		this.xxclyj = xxclyj;
	}
	/**
	 * @return the xxclr
	 */
	public String getXxclr() {
		return xxclr;
	}
	/**
	 * @param xxclr the xxclr to set
	 */
	public void setXxclr(String xxclr) {
		this.xxclr = xxclr;
	}
	/**
	 * @return the xxclsj
	 */
	public Date getXxclsj() {
		return xxclsj;
	}
	/**
	 * @param xxclsj the xxclsj to set
	 */
	public void setXxclsj(Date xxclsj) {
		this.xxclsj = xxclsj;
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
	 * @return the kkxy
	 */
	public String getKkxy() {
		return kkxy;
	}
	/**
	 * @param kkxy the kkxy to set
	 */
	public void setKkxy(String kkxy) {
		this.kkxy = kkxy;
	}
	/**
	 * @return the skbj
	 */
	public String getSkbj() {
		return skbj;
	}
	/**
	 * @param skbj the skbj to set
	 */
	public void setSkbj(String skbj) {
		this.skbj = skbj;
	}
	public String getXsjls() {
		return xsjls;
	}
	public void setXsjls(String xsjls) {
		this.xsjls = xsjls;
	}
	public String getZcs() {
		return zcs;
	}
	public void setZcs(String zcs) {
		this.zcs = zcs;
	}
	public String getCzwts() {
		return czwts;
	}
	public void setCzwts(String czwts) {
		this.czwts = czwts;
	}
	public String getZcbl() {
		return zcbl;
	}
	public void setZcbl(String zcbl) {
		this.zcbl = zcbl;
	}
	public String getXsrws() {
		return xsrws;
	}
	public void setXsrws(String xsrws) {
		this.xsrws = xsrws;
	}

}
