package com.zfsoft.monitor.entity;


/**
 * 
 * @author Administrator
 *
 */
public enum PatrolType {
    dept("学院"), school("学校");
    
    private String text;
    
    private PatrolType(String text){
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
