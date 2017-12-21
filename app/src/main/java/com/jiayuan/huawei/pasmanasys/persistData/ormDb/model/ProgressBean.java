package com.jiayuan.huawei.pasmanasys.persistData.ormDb.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
@DatabaseTable(tableName = "progress")
public class ProgressBean extends BaseModel {
    @DatabaseField(defaultValue = "")
    public String where;
    @DatabaseField(defaultValue = "")
    public String userName;
    @DatabaseField(defaultValue = "")
    public String progress;
    @DatabaseField(defaultValue = "")
    public String more;
}
