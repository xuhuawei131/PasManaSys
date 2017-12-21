package com.jiayuan.huawei.hwframeworklib.adapter.itemdecorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class GrideSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int vSpace;

    public GrideSpaceItemDecoration(int space) {
        this.space = space;
        this.vSpace = space * 3;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.top = vSpace ;
//        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) % 2 == 1) {
//            outRect.right=outRect.width()+space;
            outRect.left = space;
        }
    }

}
