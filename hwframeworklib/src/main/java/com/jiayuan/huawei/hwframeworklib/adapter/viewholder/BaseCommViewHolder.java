package com.jiayuan.huawei.hwframeworklib.adapter.viewholder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseCommAdapter;
import com.jiayuan.huawei.hwframeworklib.ui.interfaces.MyBaseContext;


/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public  abstract  class BaseCommViewHolder<DataType> extends RecyclerView.ViewHolder implements View.OnClickListener
{
    protected MyBaseCommAdapter adapter;
    protected DataType bean;
    protected int position;
    protected MyBaseContext context;
    public BaseCommViewHolder(MyBaseCommAdapter adapter, View itemView) {
        super(itemView);
        this.adapter=adapter;
        this.context=adapter.getActivity();
        findViewByIds();
        itemView.setOnClickListener(this);
    }
    protected <T extends View>T findViewById(int res){
        return (T)itemView.findViewById(res);
    }

    /**
     * 对view进行初始化
     */
    protected abstract void findViewByIds();

    /**
     * 在这里进行设置view的数据
     */
    protected abstract void setItemData();

    /**
     * item的点击
     * @param position
     * @param bean
     * @param view
     */
    protected abstract void onItemClick(int position,DataType bean,View view);

    public  void setData(int position,DataType bean){
        this.bean=bean;
        this.position=position;
        setItemData();
    }

    @Override
    public void onClick(View v) {
        if(v==itemView){
            onItemClick(position,bean,itemView);
        }
    }
}
