package com.zfsoft.evaluation.entity;

import com.zfsoft.dao.query.BaseQuery;
/**
 * 反馈意见查询类
 * @author Administrator
 *
 */
public class ClearDataQuery extends BaseQuery{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	// 存档记录ID
	private String id;
	// 学年
	private String xn;
	// 学期
	private String xq;
	// 学号
	private String xh;
	// 学生姓名
	private String xsxm;
	// 教师职工号
	private String jszgh;
	// 教师姓名
	private String jsxm;
	// 院系
	private String yx;
	// 教学班
	private String jxb;
	// 存档时间
	private String cdsj;
	// 存档类型
	private String cdlx;
	// 成绩
	private String cj;
	// 成绩备注
	private String cjbz;
	// 绩点
	private String jd;
	// 绝对值
	private String jdz;
	// 条件
    private String condition;
    // 条件2
    private String condition2;
    // 是否清洗
	private String sfqx;
	// 课程代码
	private String kcdm;
	// 课程名称
	private String kcmc;
	// 清洗条件
	private String qxtj;
	// 清洗条件组
	private String[] qxtjz;
    
	//==================================
	// 条件ID
	private String tjid;
	// 条件名称
	private String tjmc;
	// 条件表达式
	private String tjbds;
	// 状态
	private String zt;
	
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn the xn to set
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the jszgh
	 */
	public String getJszgh() {
		return jszgh;
	}
	/**
	 * @param jszgh the jszgh to set
	 */
	public void setJszgh(String jszgh) {
		this.jszgh = jszgh;
	}
	/**
	 * @return the yx
	 */
	public String getYx() {
		return yx;
	}
	/**
	 * @param yx the yx to set
	 */
	public void setYx(String yx) {
		this.yx = yx;
	}
	/**
	 * @return the cdsj
	 */
	public String getCdsj() {
		return cdsj;
	}
	/**
	 * @param cdsj the cdsj to set
	 */
	public void setCdsj(String cdsj) {
		this.cdsj = cdsj;
	}
	/**
	 * @return the tjid
	 */
	public String getTjid() {
		return tjid;
	}
	/**
	 * @param tjid the tjid to set
	 */
	public void setTjid(String tjid) {
		this.tjid = tjid;
	}
	/**
	 * @return the tjmc
	 */
	public String getTjmc() {
		return tjmc;
	}
	/**
	 * @param tjmc the tjmc to set
	 */
	public void setTjmc(String tjmc) {
		this.tjmc = tjmc;
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
	 * @return the cj
	 */
	public String getCj() {
		return cj;
	}
	/**
	 * @param cj the cj to set
	 */
	public void setCj(String cj) {
		this.cj = cj;
	}
	/**
	 * @return the cjbz
	 */
	public String getCjbz() {
		return cjbz;
	}
	/**
	 * @param cjbz the cjbz to set
	 */
	public void setCjbz(String cjbz) {
		this.cjbz = cjbz;
	}
	/**
	 * @return the jd
	 */
	public String getJd() {
		return jd;
	}
	/**
	 * @param jd the jd to set
	 */
	public void setJd(String jd) {
		this.jd = jd;
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
	 * @return the jdz
	 */
	public String getJdz() {
		return jdz;
	}
	/**
	 * @param jdz the jdz to set
	 */
	public void setJdz(String jdz) {
		this.jdz = jdz;
	}
	/**
	 * @return the sfqx
	 */
	public String getSfqx() {
		return sfqx;
	}
	/**
	 * @param sfqx the sfqx to set
	 */
	public void setSfqx(String sfqx) {
		this.sfqx = sfqx;
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
	 * @return the qxtj
	 */
	public String getQxtj() {
		return qxtj;
	}
	/**
	 * @param qxtj the qxtj to set
	 */
	public void setQxtj(String qxtj) {
		this.qxtj = qxtj;
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
	 * @return the cdlx
	 */
	public String getCdlx() {
		return cdlx;
	}
	/**
	 * @param cdlx the cdlx to set
	 */
	public void setCdlx(String cdlx) {
		this.cdlx = cdlx;
	}
	/**
	 * @return the tjbds
	 */
	public String getTjbds() {
		return tjbds;
	}
	/**
	 * @param tjbds the tjbds to set
	 */
	public void setTjbds(String tjbds) {
		this.tjbds = tjbds;
	}
	/**
	 * @return the qxtjz
	 */
	public String[] getQxtjz() {
		return qxtjz;
	}
	/**
	 * @param qxtjz the qxtjz to set
	 */
	public void setQxtjz(String[] qxtjz) {
		this.qxtjz = qxtjz;
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
	 * @return the jxb
	 */
	public String getJxb() {
		return jxb;
	}
	/**
	 * @param jxb the jxb to set
	 */
	public void setJxb(String jxb) {
		this.jxb = jxb;
	}
	/**
	 * @return the condition2
	 */
	public String getCondition2() {
		return condition2;
	}
	/**
	 * @param condition2 the condition2 to set
	 */
	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}
	
}
