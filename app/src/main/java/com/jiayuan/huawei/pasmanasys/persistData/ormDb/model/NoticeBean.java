package com.jiayuan.huawei.pasmanasys.persistData.ormDb.model;

import com.j256.ormlite.field.DatabaseField;


/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class NoticeBean extends BaseModel {
    @DatabaseField(defaultValue = "")
    public String title;
}
