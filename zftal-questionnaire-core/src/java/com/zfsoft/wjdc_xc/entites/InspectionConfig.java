package com.zfsoft.wjdc_xc.entites;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public class InspectionConfig {
	private String key;
	private String value;
	private String append;
	private String configType;
	private String remark;
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 返回
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置
	 * @param key 
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 返回
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置
	 * @param value 
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 返回
	 */
	public String getAppend() {
		return append;
	}
	/**
	 * 设置
	 * @param append 
	 */
	public void setAppend(String append) {
		this.append = append;
	}
	/**
	 * 返回
	 */
	public String getConfigType() {
		return configType;
	}
	/**
	 * 设置
	 * @param configType 
	 */
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	
	
}
