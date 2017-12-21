package com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.jiayuan.huawei.pasmanasys.MyApp;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.OrmPswword;

import java.sql.SQLException;
import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class PasswordDao extends BaseDao<OrmPswword> {
    private static PasswordDao instance = null;

    private PasswordDao() {
        super();
    }

    public static PasswordDao getInstance() {
        if (instance == null) {
            instance = new PasswordDao();
        }
        return instance;
    }

    public synchronized List<OrmPswword> getOrmPswwordList(long time) {
        QueryBuilder<OrmPswword, Integer> qb = baseDao.queryBuilder();
        try {
            if (time != 0) {
                qb.where().lt("modifyDate", time);//qb.where().lt("sendtime",time);
            } else {
                qb.where().gt("modifyDate", time);//qb.where().lt("sendtime",time);
            }
            qb.orderBy("modifyDate", false);
            qb.offset(0L).limit(max_count);
            return getQuerryInfo(qb);
        } catch (SQLException e) {
            return null;
        }
    }

    public synchronized void updateOrmPswword(OrmPswword ormPswword) {
        try {
            baseDao.update(ormPswword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean deleteOrmPswword(OrmPswword ormPswword) {
        try {
            return baseDao.delete(ormPswword)>0;
//            db.where().eq("where", ormPswword.where).and().eq("account", ormPswword.account);
//            return deleteData(db) > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
