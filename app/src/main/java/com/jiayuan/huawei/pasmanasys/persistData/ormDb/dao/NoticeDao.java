package com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.NoticeBean;

import java.sql.SQLException;
import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class NoticeDao extends BaseDao<NoticeBean> {
    private static NoticeDao instance=null;
    private NoticeDao(){
        super();
    }
    public static NoticeDao getInstance(){
        if(instance==null){
            instance=new NoticeDao();
        }
        return instance;
    }

    public synchronized List<NoticeBean> getNoticeList(long time) {
        QueryBuilder<NoticeBean, Integer> qb = baseDao.queryBuilder();
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

    public synchronized void updateNotice(NoticeBean bean) {
        try {
            baseDao.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean deleteNotice(NoticeBean bean) {
        try {
            return baseDao.delete(bean) > 0;
//            db.where().eq("where", ormPswword.where).and().eq("account", ormPswword.account);
//            return deleteData(db) > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
