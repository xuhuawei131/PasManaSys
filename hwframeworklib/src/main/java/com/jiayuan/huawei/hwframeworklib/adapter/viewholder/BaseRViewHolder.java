package com.jiayuan.huawei.hwframeworklib.adapter.viewholder;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;


/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public   abstract class BaseRViewHolder<DataType> extends RecyclerView.ViewHolder implements View.OnLongClickListener,View.OnClickListener
{
    protected MyBaseRecyclerViewAdapter adapter;
    protected DataType bean;
    protected int position;
    public BaseRViewHolder(MyBaseRecyclerViewAdapter adapter, View itemView) {
        super(itemView);
        this.adapter=adapter;
        findViewByIds();
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
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
    protected abstract void onLongItemClick(int position,DataType bean,View view);
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

    @Override
    public boolean onLongClick(View v) {
        if(v==itemView){
            onLongItemClick(position,bean,itemView);
        }
        return false;
    }
}
