package com.zfsoft.monitor.entity;

/**
 * 
 * 类描述：状态枚举类
 * 
 * @version: 1.0
 * @author: yingjie.fan
 * @version: 2015-12-23 下午01:43:43
 */
public enum PatrolDetailStatusEnum {
	STATUS_WSB("未上报", "0"), // 未上报状态
	STATUS_YSB("已上报", "1"), // 已上报状态
	STATUS_YXYCL("院系已处理", "2"), // 院系已处理
	STATUS_XXYCL("学校已处理", "3"); // 学校已处理

	/**
	 * 定义枚举类型自己的属性
	 */
	private final String text;
	private final String key;

	private PatrolDetailStatusEnum(String text, String key) {
		this.text = text;
		this.key = key;
	}

	/**
	 * 展示文本
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * 代码编号
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

}
