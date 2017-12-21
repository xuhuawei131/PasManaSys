package com.jiayuan.huawei.pasmanasys.ui.fragments;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.hwframeworklib.ui.fragments.BaseRefreshAndMoreFragment;
import com.jiayuan.huawei.hwframeworklib.utis.HWUtils;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class AppBaseFragment<T extends MyBaseRecyclerViewAdapter> extends BaseRefreshAndMoreFragment<T> {
    protected View emptyView;

    public interface OnItemDeleteListener {
        public void onItemDelete(int position);
    }

    protected void deleteItem(final int position, String content, @NonNull final OnItemDeleteListener listener) {
        if (HWUtils.isEmpty(content)) {
            return;
        }
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setTitle("是否删除该项目！");
        int length = content.length();
        if (length > 10) {
            content = content.substring(0, 10);
        }
        ab.setMessage(content);
        ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onItemDelete(position);
            }
        });
        ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ab.create().show();
    }


}
