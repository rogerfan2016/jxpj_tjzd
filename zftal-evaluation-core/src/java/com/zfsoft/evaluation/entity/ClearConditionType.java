package com.zfsoft.evaluation.entity;


/**
 * 
 * @author Administrator
 *
 */
public enum ClearConditionType {
	CONDITION_CJ("成绩","cj"),
    CONDITION_JD("绩点","jd"),
    CONDITION_JDZ("绝对值","jdz"),
    CONDITION_QXKSZG("取消考试资格","cjbz");

	/**
	 * 定义枚举类型自己的属性
	 */
	private final String text;
	private final String key;

	private ClearConditionType(String text, String key) {
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
