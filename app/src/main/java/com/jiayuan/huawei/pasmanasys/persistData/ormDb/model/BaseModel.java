package com.jiayuan.huawei.pasmanasys.persistData.ormDb.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class BaseModel implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(defaultValue = "")
    public String content;
    @DatabaseField
    public long createDate;
    @DatabaseField
    public long modifyDate;
}
