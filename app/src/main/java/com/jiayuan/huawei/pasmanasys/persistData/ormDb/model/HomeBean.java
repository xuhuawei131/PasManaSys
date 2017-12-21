package com.jiayuan.huawei.pasmanasys.persistData.ormDb.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
@DatabaseTable(tableName = "home")
public class HomeBean  extends BaseModel {
    @DatabaseField
    public String title;
}
