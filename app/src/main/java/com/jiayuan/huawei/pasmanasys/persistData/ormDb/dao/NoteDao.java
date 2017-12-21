package com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.NoteLife;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.OrmPswword;

import java.sql.SQLException;
import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class NoteDao extends BaseDao<NoteLife> {
    private static NoteDao instance = null;
    private NoteDao() {
        super();
    }
    public static NoteDao getInstance() {
        if (instance == null) {
            instance = new NoteDao();
        }
        return instance;
    }

    public synchronized List<NoteLife> getNoteList(long time) {
        QueryBuilder<NoteLife, Integer> qb = baseDao.queryBuilder();
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

    public synchronized void updateOrmPswword(NoteLife bean) {
        try {
            baseDao.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean deleteOrmPswword(NoteLife bean) {
        try {
            return baseDao.delete(bean) > 0;
//            db.where().eq("where", ormPswword.where).and().eq("account", ormPswword.account);
//            return deleteData(db) > 0;
        } catch (SQLException e) {
            return false;
        }
    }

}
