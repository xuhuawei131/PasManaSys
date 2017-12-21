package com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao;


import com.j256.ormlite.stmt.QueryBuilder;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.ProgressBean;

import java.sql.SQLException;
import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class ProgressDao extends BaseDao<ProgressBean> {
    private static ProgressDao instance = null;

    private ProgressDao() {
        super();
    }

    public static ProgressDao getInstance() {
        if (instance == null) {
            instance = new ProgressDao();
        }
        return instance;
    }

    public synchronized List<ProgressBean> getProgressList(long time) {
        QueryBuilder<ProgressBean, Integer> qb = baseDao.queryBuilder();
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

    public synchronized void updateProgress(ProgressBean progress) {
        try {
            baseDao.update(progress);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public synchronized boolean deleteProgress(ProgressBean progressBean) {
        try {
            return baseDao.delete(progressBean) > 0;
        } catch (SQLException e) {
            return false;
        }
    }

}
