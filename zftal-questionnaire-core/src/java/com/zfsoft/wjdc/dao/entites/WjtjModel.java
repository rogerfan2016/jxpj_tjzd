package com.zfsoft.wjdc.dao.entites;

import com.zfsoft.common.query.ModelBase;

public class WjtjModel extends ModelBase{

	private static final long serialVersionUID = 1L;
	
	private String wjid;//问卷id
	private String lxid;//类型（数据源）
	private String djzt;//答卷状态
	
	private String[] groupFields;//分组字段-用于答卷统计
	private String sql;//用于纯sql获取结果集
	
	private String stidx;//试题id x轴，用于问卷试题交叉统计x
	private String stidy;//试题id y轴，用户问卷统计交叉统计y
	
	private String tjtype;//统计类型，用于配置类型的统计
	
	public final String getWjid() {
		return wjid;
	}
	public final void setWjid(String wjid) {
		this.wjid = wjid;
	}
	public final String getLxid() {
		return lxid;
	}
	public final void setLxid(String lxid) {
		this.lxid = lxid;
	}
	public final String[] getGroupFields() {
		if(groupFields==null
		||(groupFields.length==1&&groupFields[0]!=null&&"".equals(groupFields[0].trim()))){
			groupFields=new String[0];
		}
		return groupFields;
	}
	public final void setGroupFields(String[] groupFields) {
		this.groupFields = groupFields;
	}
	public final String getSql() {
		return sql;
	}
	public final void setSql(String sql) {
		this.sql = sql;
	}
	public final String getDjzt() {
		return djzt;
	}
	public final void setDjzt(String djzt) {
		this.djzt = djzt;
	}
	public final String getStidx() {
		return stidx;
	}
	public final void setStidx(String stidx) {
		this.stidx = stidx;
	}
	public final String getStidy() {
		return stidy;
	}
	public final void setStidy(String stidy) {
		this.stidy = stidy;
	}
	public final String getTjtype() {
		return tjtype;
	}
	public final void setTjtype(String tjtype) {
		this.tjtype = tjtype;
	}

}
