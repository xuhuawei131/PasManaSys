package com.jiayuan.huawei.pasmanasys.persistData.ormDb.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jiayuan.huawei.hwframeworklib.others.security._3AES;
import com.jiayuan.huawei.pasmanasys.constants.MyConstatns;
import com.jiayuan.huawei.pasmanasys.persistData.comDb.tables.PasswordTable;

import java.io.Serializable;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
@DatabaseTable(tableName = "ormpassword")
public class OrmPswword extends BaseModel {
    @DatabaseField(defaultValue = "")
    public String password;
    @DatabaseField(defaultValue = "")
    public String account;
    @DatabaseField(defaultValue = "")
    public String userName;
    @DatabaseField(defaultValue = "")
    public String where;
    @DatabaseField(defaultValue = "")
    public String more;

}
