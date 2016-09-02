package com.zfsoft.wjdc_xc.query;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-15
 * @version V1.0.0
 */
public class InspectionSummerQuery extends BaseQuery{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2302329540178971482L;
	private Map<String, String> params = new HashMap<String, String>();
	private String express;
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
}
