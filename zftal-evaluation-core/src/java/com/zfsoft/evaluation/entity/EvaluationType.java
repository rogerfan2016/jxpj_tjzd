package com.zfsoft.evaluation.entity;


/**
 * 
 * @author Administrator
 *
 */
public enum EvaluationType {
    SSPJ("学生实时评教"),
    DDPJ("督导评教"),
    JSHP("教师互评_默认评价模板"),
    JSHP_ZYLLK("教师互评_专业理论课"),
    JSHP_ZYSJK("教师互评_专业实践课"),
    JSHP_GGK("教师互评_公共课"),
    JSHP_TYK("教师互评_体育课"),
    JSHP_JXZGK("教师互评_教学做（工科）"),
    JSHP_JXZWK("教师互评_教学做（文科）");

    private String text;
    
    private EvaluationType(String text){
        this.text=text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getKeyStr() {
        return toString();
    }
    
}
