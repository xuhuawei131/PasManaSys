package com.jiayuan.huawei.pasmanasys.persistData.comDb.impleDao;

import android.content.ContentValues;

import com.jiayuan.huawei.hwframeworklib.persistData.commdb.impleDao.BaseDBImpl;
import com.jiayuan.huawei.pasmanasys.persistData.comDb.model.PasswordBean;
import com.jiayuan.huawei.pasmanasys.persistData.comDb.tables.PasswordTable;

import net.sqlcipher.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class PasswordDao extends BaseDBImpl<PasswordBean> implements PasswordTable {
    private static PasswordDao instance = null;

    public static PasswordDao getInstance() {
        if (instance == null) {
            instance = new PasswordDao();
        }
        return instance;
    }

    @Override
    public long inserData(PasswordBean info) {
        database = getDataBase();
        if (isExistData(info)) {
            return updateData(info);
        } else {
            ContentValues values = dataBean2ContentValues(info);
            long id = database.insert(PASS_TABLE_NAME, null, values);
            return id;
        }
    }

    @Override
    public int updateData(PasswordBean info) {
        database = getDataBase();
        ContentValues values = dataBean2ContentValues(info);
        return database.update(PASS_TABLE_NAME, values, info + " = ?", new String[]{info.userName + ""});
    }

    @Override
    public boolean isExistData(PasswordBean info) {
        boolean isExist = false;
        database = getDataBase();
        String sql = "SELECT * FROM " + PASS_TABLE_NAME + " WHERE "
                + PASS_USERNAME + " = ?";
        Cursor c = database.rawQuery(sql, new String[]{info.userName + ""});
        if (c != null && c.moveToNext()) {
            isExist = true;
        }
        if (c != null) {
            c.close();
        }
        return isExist;
    }

    /**
     * 获取所有的密码数据列表
     * @return
     */
    public List<PasswordBean> getAllPasswordList() {
        StringBuilder sb = new StringBuilder("SELECT * FROM " + PASS_TABLE_NAME);
        String sql = sb.toString();
        List<PasswordBean> arrayList = new ArrayList<>();
        database = getDataBase();
        Cursor c = database.rawQuery(sql, null);
        if (c != null) {
            while (c.moveToNext()) {
                PasswordBean bean = cursor2DataBean(c);
                arrayList.add(bean);
            }
            c.close();
        }
        return arrayList;
    }

    @Override
    public ContentValues dataBean2ContentValues(PasswordBean info) {
        ContentValues values = new ContentValues();
        values.put(PASS_ACCOUNT, info.account);
        values.put(PASS_PASSWORD, info.password);
        values.put(PASS_USERNAME, info.userName);
        values.put(PASS_WHERE, info.where);
        values.put(_ID, info.ID);
        values.put(MORE, info.MORE);
        return values;
    }

    @Override
    public PasswordBean cursor2DataBean(Cursor cursor) {
        PasswordBean info = new PasswordBean();
        info.account = cursor.getString(cursor.getColumnIndex(PASS_ACCOUNT));
        info.account = cursor.getString(cursor.getColumnIndex(PASS_PASSWORD));
        info.account = cursor.getString(cursor.getColumnIndex(PASS_USERNAME));
        info.account = cursor.getString(cursor.getColumnIndex(PASS_WHERE));
        info.account = cursor.getString(cursor.getColumnIndex(_ID));
        info.account = cursor.getString(cursor.getColumnIndex(MORE));
        return info;
    }
}
