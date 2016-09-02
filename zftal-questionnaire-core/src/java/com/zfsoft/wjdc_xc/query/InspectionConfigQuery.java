package com.zfsoft.wjdc_xc.query;

import java.util.HashMap;
import java.util.Map;
import com.zfsoft.dao.query.BaseQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-5
 * @version V1.0.0
 */
public class InspectionConfigQuery extends BaseQuery{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7444492061972148302L;
	private String table;
	private String express;
	private Map<String, String> params = new HashMap<String, String>();
	/**
	 * 返回
	 */
	public String getTable() {
		return table;
	}
	/**
	 * 设置
	 * @param table 
	 */
	public void setTable(String table) {
		this.table = table;
	}
	/**
	 * 返回
	 */
	public String getExpress() {
		return express;
	}
	/**
	 * 设置
	 * @param express 
	 */
	public void setExpress(String express) {
		this.express = express;
	}
	/**
	 * 返回
	 */
	public Map<String, String> getParams() {
		return params;
	}
	/**
	 * 设置
	 * @param params 
	 */
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
