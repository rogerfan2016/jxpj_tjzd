package com.zfsoft.evaluation.entity;

import java.io.Serializable;

/**
* <p>Title: ClearDataEntity</p>
* <p>Description: 数据清洗存档实体类</p>
* <p>Company: XXXX</p>
* @author    rogerfan
* @date      2016-1-8
 */
public class ClearDataEntity implements Serializable{
	
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
	// 选课课号
	private String xkkh;
	// 课程代码
	private String kcdm;
	// 课程名称
	private String kcmc;
	// 教师职工号
	private String jszgh;
	// 教师姓名
	private String jsxm;
	// 院系
	private String yx;
	// 教学班（教师）
	private String jxbjs;
	// 教学班（课程）
	private String jxbkc;
	// 总分
	private String zf;
	// 是否清洗
	private String sfqx;
	// 清洗条件
	private String qxtj;
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
	//==================================
	// 方差
	private String fc;
	// 众数
	private String zs;
	// 评价总数
	private String pjzs;
	// 清洗数量
	private String qxsl;
	// 有效数量
	private String yxsl;
	// 最大值
	private String zdz;
	// 最小值
	private String zxz;
	//==================================
	// 条件ID
	private String tjid;
	// 条件名称
	private String tjmc;
	// 条件表达式
	private String tjbds;
	// 状态
	private String zt;
	//==================================
	// 授课教师数量
	private String skjssl;
	// 授课数量
	private String sksl;
	// 教学班数量
	private String jxbsl;
	
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
	 * @return the xkkh
	 */
	public String getXkkh() {
		return xkkh;
	}
	/**
	 * @param xkkh the xkkh to set
	 */
	public void setXkkh(String xkkh) {
		this.xkkh = xkkh;
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
	 * @return the zf
	 */
	public String getZf() {
		return zf;
	}
	/**
	 * @param zf the zf to set
	 */
	public void setZf(String zf) {
		this.zf = zf;
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
	 * @return the skjssl
	 */
	public String getSkjssl() {
		return skjssl;
	}
	/**
	 * @param skjssl the skjssl to set
	 */
	public void setSkjssl(String skjssl) {
		this.skjssl = skjssl;
	}
	/**
	 * @return the sksl
	 */
	public String getSksl() {
		return sksl;
	}
	/**
	 * @param sksl the sksl to set
	 */
	public void setSksl(String sksl) {
		this.sksl = sksl;
	}
	/**
	 * @return the jxbsl
	 */
	public String getJxbsl() {
		return jxbsl;
	}
	/**
	 * @param jxbsl the jxbsl to set
	 */
	public void setJxbsl(String jxbsl) {
		this.jxbsl = jxbsl;
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
	 * @return the jxbjs
	 */
	public String getJxbjs() {
		return jxbjs;
	}
	/**
	 * @param jxbjs the jxbjs to set
	 */
	public void setJxbjs(String jxbjs) {
		this.jxbjs = jxbjs;
	}
	/**
	 * @return the jxbkc
	 */
	public String getJxbkc() {
		return jxbkc;
	}
	/**
	 * @param jxbkc the jxbkc to set
	 */
	public void setJxbkc(String jxbkc) {
		this.jxbkc = jxbkc;
	}
	/**
	 * @return the zs
	 */
	public String getZs() {
		return zs;
	}
	/**
	 * @param zs the zs to set
	 */
	public void setZs(String zs) {
		this.zs = zs;
	}
	/**
	 * @return the zdz
	 */
	public String getZdz() {
		return zdz;
	}
	/**
	 * @param zdz the zdz to set
	 */
	public void setZdz(String zdz) {
		this.zdz = zdz;
	}
	/**
	 * @return the zxz
	 */
	public String getZxz() {
		return zxz;
	}
	/**
	 * @param zxz the zxz to set
	 */
	public void setZxz(String zxz) {
		this.zxz = zxz;
	}
	/**
	 * @return the pjzs
	 */
	public String getPjzs() {
		return pjzs;
	}
	/**
	 * @param pjzs the pjzs to set
	 */
	public void setPjzs(String pjzs) {
		this.pjzs = pjzs;
	}
	/**
	 * @return the qxsl
	 */
	public String getQxsl() {
		return qxsl;
	}
	/**
	 * @param qxsl the qxsl to set
	 */
	public void setQxsl(String qxsl) {
		this.qxsl = qxsl;
	}
	/**
	 * @return the fc
	 */
	public String getFc() {
		return fc;
	}
	/**
	 * @param fc the fc to set
	 */
	public void setFc(String fc) {
		this.fc = fc;
	}
	/**
	 * @return the yxsl
	 */
	public String getYxsl() {
		return yxsl;
	}
	/**
	 * @param yxsl the yxsl to set
	 */
	public void setYxsl(String yxsl) {
		this.yxsl = yxsl;
	}

}
