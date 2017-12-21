package com.jiayuan.huawei.hwframeworklib.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.jiayuan.huawei.hwframeworklib.adapter.viewholder.BaseCommViewHolder;

import java.util.List;

/**
 * 不用指定activity的适配器
 * @param <VH>
 * @param <Param>
 */
public abstract class MyBaseCommAdapterWithoutActivity<VH extends BaseCommViewHolder,Param> extends BaseAdapter {
	protected Context context;
	protected OnClickListener listener;
	protected List<Param> arrayList;
	private  LayoutInflater inflater;
	protected abstract VH createViewHolder(int position,Param bean,ViewGroup viewgroup);
	protected abstract void bindViewHolder(int position,Param bean,VH holder);
	
	public MyBaseCommAdapterWithoutActivity(Context context, List<Param> arrayList){
		this.context=context;
		this.inflater=LayoutInflater.from(context);
		this.arrayList=arrayList;
	}
	public void setOnClickListener(OnClickListener listener){
		this.listener=listener;
	}
	@Override
	public int getCount() {
		return arrayList==null?0:arrayList.size();
	}

	@Override
	public Param getItem(int arg0) {
		return arrayList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	protected View inflateView(int resId,ViewGroup viewgroup){
		return inflater.inflate(resId, viewgroup);
	}
	@Override
	public View getView(int position, View converView, ViewGroup viewgroup) {
		VH holder;
		Param bean=arrayList.get(position);
		if(converView==null){
			holder=createViewHolder(position,bean,viewgroup);
			converView=holder.itemView;
			converView.setTag(holder);
		}else{
			holder=(VH)converView.getTag();
		}
		bindViewHolder(position,bean,holder);
		return converView;
	}
	
}