package com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao;

import android.util.Log;

import com.j256.ormlite.stmt.QueryBuilder;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.HomeBean;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.OrmPswword;

import java.sql.SQLException;
import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class HomeDao extends BaseDao<HomeBean> {
    private static HomeDao instance=null;
    private HomeDao(){
        super();
    }
    public static HomeDao getInstance(){
        if(instance==null){
            instance=new HomeDao();
        }
        return instance;
    }

    public synchronized List<HomeBean> getHomeList(long time) {
        QueryBuilder<HomeBean, Integer> qb = baseDao.queryBuilder();
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
            System.out.print("msg:"+e.getLocalizedMessage());
            return null;
        }
    }

    public synchronized void updateHome(HomeBean bean) {
        try {
            baseDao.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean deleteHome(HomeBean bean) {
        try {
            return baseDao.delete(bean) > 0;
//            db.where().eq("where", ormPswword.where).and().eq("account", ormPswword.account);
//            return deleteData(db) > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
