package com.jiayuan.huawei.hwframeworklib.beans;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class EditCheckBean {
    private String key;
    private String values;
    public EditCheckBean(String key,String values){
        this.key=key;
        this.values=values;
    }
    public String getKey(){
        return key;
    }
    public String getValues(){
        return values;
    }
}
