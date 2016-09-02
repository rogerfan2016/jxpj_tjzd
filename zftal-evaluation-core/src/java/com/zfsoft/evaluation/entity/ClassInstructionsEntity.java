package com.zfsoft.evaluation.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 
* <p>Title: ClassInstructionsEntity</p>
* <p>Description: 任课说明书实体类</p>
* <p>Company: XXXX</p>
* @author    rogerfan
* @date      2016-1-21
 */
public class ClassInstructionsEntity extends BaseQuery implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	// ID
	private String id;
	// 学年
	private String xn;
	// 学期
	private String xq;
	// 院系
	private String yx;
	// 专业
	private String zy;
	// 年级
	private String nj;
	// 班级
	private String bj;
	// 课程名称
	private String kcmc;
	// 任课教师
	private String rkjs;
	// 讲课周次
	private String jkzc;
	// 开始周
	private String ksz;
	// 结束周
	private String jsz;
	// 总学时
	private String zxs;
	// 讲课学时
	private String jkxs;
	// 习题学时
	private String xtxs;
	// 课堂讨论学时
	private String kttlxs;
	// 案例教学学时
	private String aljxxs;
	// 教学要求
	private String jxyq;
	// 课程在专业中的作用
	private String kczy;
	// 教材及参考书
	private String jccks;
	// 院系审核人
	private String yxshr;
	// 院系审核时间
	private Date yxshsj;
	// 状态（0未提交；1已提交；2教研室已审核；3院系已审核）
	private String zt;
	// 教研室审核人
	private String jysshr;
	// 教研室审核时间
	private Date jysshsj;
	// 创建人
	private String cjr;
	// 创建时间
	private Date cjsj;
	// 条件
	private String condition;
	// 审核意见
	private String shyj;
	
	// 任课说明书详情
	private List<ClassInstructionsDetailEntity> detailList;
	
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
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param nj the nj to set
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the bj
	 */
	public String getBj() {
		return bj;
	}
	/**
	 * @param bj the bj to set
	 */
	public void setBj(String bj) {
		this.bj = bj;
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
	 * @return the rkjs
	 */
	public String getRkjs() {
		return rkjs;
	}
	/**
	 * @param rkjs the rkjs to set
	 */
	public void setRkjs(String rkjs) {
		this.rkjs = rkjs;
	}
	/**
	 * @return the ksz
	 */
	public String getKsz() {
		return ksz;
	}
	/**
	 * @param ksz the ksz to set
	 */
	public void setKsz(String ksz) {
		this.ksz = ksz;
	}
	/**
	 * @return the jsz
	 */
	public String getJsz() {
		return jsz;
	}
	/**
	 * @param jsz the jsz to set
	 */
	public void setJsz(String jsz) {
		this.jsz = jsz;
	}
	/**
	 * @return the zxs
	 */
	public String getZxs() {
		return zxs;
	}
	/**
	 * @param zxs the zxs to set
	 */
	public void setZxs(String zxs) {
		this.zxs = zxs;
	}
	/**
	 * @return the jkxs
	 */
	public String getJkxs() {
		return jkxs;
	}
	/**
	 * @param jkxs the jkxs to set
	 */
	public void setJkxs(String jkxs) {
		this.jkxs = jkxs;
	}
	/**
	 * @return the xtxs
	 */
	public String getXtxs() {
		return xtxs;
	}
	/**
	 * @param xtxs the xtxs to set
	 */
	public void setXtxs(String xtxs) {
		this.xtxs = xtxs;
	}
	/**
	 * @return the kttlxs
	 */
	public String getKttlxs() {
		return kttlxs;
	}
	/**
	 * @param kttlxs the kttlxs to set
	 */
	public void setKttlxs(String kttlxs) {
		this.kttlxs = kttlxs;
	}
	/**
	 * @return the aljxxs
	 */
	public String getAljxxs() {
		return aljxxs;
	}
	/**
	 * @param aljxxs the aljxxs to set
	 */
	public void setAljxxs(String aljxxs) {
		this.aljxxs = aljxxs;
	}
	/**
	 * @return the jxyq
	 */
	public String getJxyq() {
		return jxyq;
	}
	/**
	 * @param jxyq the jxyq to set
	 */
	public void setJxyq(String jxyq) {
		this.jxyq = jxyq;
	}
	/**
	 * @return the kczy
	 */
	public String getKczy() {
		return kczy;
	}
	/**
	 * @param kczy the kczy to set
	 */
	public void setKczy(String kczy) {
		this.kczy = kczy;
	}
	/**
	 * @return the jccks
	 */
	public String getJccks() {
		return jccks;
	}
	/**
	 * @param jccks the jccks to set
	 */
	public void setJccks(String jccks) {
		this.jccks = jccks;
	}
	/**
	 * @return the yxshr
	 */
	public String getYxshr() {
		return yxshr;
	}
	/**
	 * @param yxshr the yxshr to set
	 */
	public void setYxshr(String yxshr) {
		this.yxshr = yxshr;
	}
	/**
	 * @return the yxshsj
	 */
	public Date getYxshsj() {
		return yxshsj;
	}
	/**
	 * @param yxshsj the yxshsj to set
	 */
	public void setYxshsj(Date yxshsj) {
		this.yxshsj = yxshsj;
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
	 * @return the jysshr
	 */
	public String getJysshr() {
		return jysshr;
	}
	/**
	 * @param jysshr the jysshr to set
	 */
	public void setJysshr(String jysshr) {
		this.jysshr = jysshr;
	}
	/**
	 * @return the jysshsj
	 */
	public Date getJysshsj() {
		return jysshsj;
	}
	/**
	 * @param jysshsj the jysshsj to set
	 */
	public void setJysshsj(Date jysshsj) {
		this.jysshsj = jysshsj;
	}
	
	/**
	 * @return the cjr
	 */
	public String getCjr() {
		return cjr;
	}
	/**
	 * @param cjr the cjr to set
	 */
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	/**
	 * @return the cjsj
	 */
	public Date getCjsj() {
		return cjsj;
	}
	/**
	 * @param cjsj the cjsj to set
	 */
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	/**
	 * @return the detailList
	 */
	public List<ClassInstructionsDetailEntity> getDetailList() {
		return detailList;
	}
	/**
	 * @param detailList the detailList to set
	 */
	public void setDetailList(List<ClassInstructionsDetailEntity> detailList) {
		this.detailList = detailList;
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
	 * @return the jkzc
	 */
	public String getJkzc() {
		return jkzc;
	}
	/**
	 * @param jkzc the jkzc to set
	 */
	public void setJkzc(String jkzc) {
		this.jkzc = jkzc;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	
}
