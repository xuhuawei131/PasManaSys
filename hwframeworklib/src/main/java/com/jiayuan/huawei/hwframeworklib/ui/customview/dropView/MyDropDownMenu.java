package com.jiayuan.huawei.hwframeworklib.ui.customview.dropView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.jiayuan.huawei.hwframeworklib.R;
import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.JRefreshLayout;
import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.PullToRefreshView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by dongjunkun on 2015/6/17.
 */
public class MyDropDownMenu extends LinearLayout implements PullToRefreshView.OnRefreshListener, View.OnClickListener, Animation.AnimationListener {
    private Context context;
    private View contentView;

    private FrameLayout layout_mask;
    private FrameLayout popupMenuViews;
    private JRefreshLayout refresh_layout;
//    /**
//     * 过滤菜单横幅
//     **/
//    private RecyclerView filterMenu;
//    private List<FilterItemBean> itemList;
//    private FilterItemRVAdapter itemAdapter;
//    /**
//     * 显示的内容
//     **/
//    private RecyclerView filterContent;
//    private List<DeviceInfoBean> contentList;
//    private FilterContentRVAdapter contentAdapter;
//    /**
//     * 弹出过滤的列表
//     **/
//    private RecyclerView popupRecyclerView;
//    private List<PopupBean> popupList;
//    private FilterPopupRVAdapter popupAdapter;
//
//    private MainActivity activity = null;
//    private boolean isOepnNewDialog = false;//关闭对话框之后从新打开新的
//    private Map<FilterItemBean, PopupBean> dataMap;
//    private Map<FilterItemBean, List<PopupBean>> allData;
//
//    private FilterItemBean currentFilterItemBean = null;

    public MyDropDownMenu(Context context) {
        super(context, null);
        initView(context);
    }

    public MyDropDownMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyDropDownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
//        activity = (MainActivity) context;
//        contentView = LayoutInflater.from(context).inflate(R.layout.layout_filter_item, this, false);
//        addView(contentView);
//
//        initData();
//        findViewByIds();
//        requestService();
    }

//    private void initData() {
//        dataMap = new ConcurrentHashMap<>();
//        allData = new ConcurrentHashMap<>();
//
//        itemList = new ArrayList<>();
//        itemAdapter = new FilterItemRVAdapter(activity, itemList);
//
//        popupList = new ArrayList<>();
//        popupAdapter = new FilterPopupRVAdapter(activity, popupList);
//
//        contentList = new ArrayList<DeviceInfoBean>();
//        contentAdapter = new FilterContentRVAdapter(activity, contentList);
//
//        //拦截条件
//        int index = 0;
//        for (String item : MyConstants.filterConditions) {
//            FilterItemBean bean = new FilterItemBean();
//            bean.index = index;
//            bean.sqlName = MyConstants.filterSqlConditions[index];
//            bean.itemName = item;
//            itemList.add(bean);
//            index++;
//        }
//    }
//
//    private void findViewByIds() {
//        EventBus.getDefault().register(this);
//        refresh_layout = (JRefreshLayout) contentView.findViewById(R.id.refresh_layout);
//        refresh_layout.setOnRefreshListener(this);
//
//        filterMenu = (RecyclerView) contentView.findViewById(R.id.filterMenu);
//        filterContent = (RecyclerView) contentView.findViewById(R.id.filterContent);
//        popupRecyclerView = (RecyclerView) contentView.findViewById(R.id.popupRecyclerView);
//
//        layout_mask = (FrameLayout) contentView.findViewById(R.id.layout_mask);
//        popupMenuViews = (FrameLayout) contentView.findViewById(R.id.popupMenuViews);
//        popupMenuViews.setOnClickListener(this);
//        //-------------------------------------------------------------------------
//        SpacesItemDecoration hDecoration = new SpacesItemDecoration(activity, LinearLayoutManager.HORIZONTAL);
//        SpacesItemDecoration vDecoration = new SpacesItemDecoration(activity, LinearLayoutManager.VERTICAL);
//
//        //-----------------顶部显示的地方----------------------
//        LinearLayoutManager itemManager = new LinearLayoutManager(activity);
//        itemManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        filterMenu.setLayoutManager(itemManager);
//        filterMenu.addItemDecoration(hDecoration);
//        filterMenu.setAdapter(itemAdapter);
//
//        //-----------------popup对话框------------------------
//        LinearLayoutManager popupManager = new LinearLayoutManager(activity);
//        popupManager.setOrientation(LinearLayoutManager.VERTICAL);
//        popupRecyclerView.setLayoutManager(popupManager);
//        popupRecyclerView.addItemDecoration(vDecoration);
//        popupRecyclerView.setAdapter(popupAdapter);
//
//        //-----------过滤内容显示的地方---------------
//        LinearLayoutManager contentManager = new LinearLayoutManager(activity);
//        contentManager.setOrientation(LinearLayoutManager.VERTICAL);
//        filterContent.setLayoutManager(contentManager);
//        filterContent.addItemDecoration(vDecoration);
//        filterContent.setAdapter(contentAdapter);
//    }
//
//    private void requestService() {
//        if (!refresh_layout.isRefreshing()) {
//            refresh_layout.setRefreshing(true);
//        }
//        Observable.just("").subscribeOn(Schedulers.io())
//                .map(new Func1<String, List<DeviceInfoBean>>() {
//                    @Override
//                    public List<DeviceInfoBean> call(String s) {
//                        //拦截条件
//                        itemList.clear();
//                        dataMap.clear();
//                        allData.clear();
//                        int index = -1;
//                        for (String item : MyConstants.filterConditions) {
//                            index++;
//                            FilterItemBean bean = new FilterItemBean();
//                            bean.index = index;
//                            bean.sqlName = MyConstants.filterSqlConditions[index];
//                            bean.itemName = item;
//                            itemList.add(bean);
//                            //------------------------每一个item对应的列表--------------------
//                            List<PopupBean> tempList = new ArrayList<PopupBean>();
//                            List<ItemResultBean> verList = DeviceInfoDAO.getInstance().getDeviceInfosByItem(bean.sqlName);
//                            int count = 0;
//                            for (ItemResultBean vitem : verList) {
//                                PopupBean pbean = new PopupBean();
//                                pbean.name = vitem.name;
//                                pbean.count = vitem.count;
//                                count += vitem.count;
//                                pbean.index = index;
//                                tempList.add(pbean);
//                            }
//                            PopupBean fBean = new PopupBean();
//                            fBean.isNo = true;
//                            fBean.isSelected = true;
//                            fBean.index = index;
//                            fBean.count = count;
//                            fBean.name = "不限";
//                            tempList.add(0, fBean);
//                            allData.put(bean, tempList);
//                            dataMap.put(bean, fBean);
//                        }
//                        List<DeviceInfoBean> deviceList = DeviceInfoDAO.getInstance().getDeviceInfosBySys("" + MyConstants.SYSTEM_ANDROID);
//                        return deviceList;
//                    }
//                }).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<List<DeviceInfoBean>>() {
//                    @Override
//                    public void call(List<DeviceInfoBean> deviceList) {
//                        refresh_layout.setRefreshing(false);
//                        contentList.clear();
////                        for (DeviceInfoBean bean : deviceList) {
////                            MainBean item = new MainBean();
////                            item.setDeviceInfoBean(bean);
////                            contentList.add(item);
////                        }
//                        contentList.addAll(deviceList);
//                        itemAdapter.notifyDataSetChanged();
//                        contentAdapter.notifyDataSetChanged();
//                    }
//                });
//    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

//    public void notifyDataSetChanged() {
//        contentList.clear();
//        List<DeviceInfoBean> deviceList = DeviceInfoDAO.getInstance().getDeviceInfosBySys("" + MyConstants.SYSTEM_ANDROID);
//        for (DeviceInfoBean bean : deviceList) {
//            MainBean item = new MainBean();
//            item.setDeviceInfoBean(bean);
//            contentList.add(item);
//        }
//        contentAdapter.notifyDataSetChanged();
//    }
//
//    private void setDropDownPopupData(FilterItemBean bean) {
//        popupList.clear();
//        popupList.addAll(allData.get(bean));
//        popupAdapter.notifyDataSetChanged();
//    }
//
//    /**
//     * 拦截菜单点击
//     *
//     * @param bean
//     */
//    @Subscriber(tag = "onFilterItemClick")
//    public void onFilterItemClick(FilterItemBean bean) {
//        if (bean.isSelected) {
//            if (isShowPopupWindow()) {
//                closePopupWindow(false);
//            }
//        } else {
//            setDropDownPopupData(bean);
//            currentFilterItemBean = bean;
//            if (popupMenuViews.getVisibility() == VISIBLE) {
//                closePopupWindow(true);
//            } else {
//                showPopupWindow();
//                itemAdapter.notifyDataSetChanged();
//            }
//        }
//    }
//
//    @Subscriber(tag = "onPopupItemClick")
//    public void onPopupItemClick(PopupBean bean) {
//        FilterItemBean item = null;
//        for (FilterItemBean temp : itemList) {
//            if (temp.index == bean.index) {
//                item = temp;
//                break;
//            }
//        }
//        for (PopupBean tempBean : popupList) {
//            tempBean.isSelected = false;
//        }
//        bean.isSelected = true;
//        popupAdapter.notifyDataSetChanged();
//        dataMap.put(item, bean);
//
//        Iterator<Map.Entry<FilterItemBean, PopupBean>> it = dataMap.entrySet().iterator();
//        Map<String, String> params = new ConcurrentHashMap<>();
//        while (it.hasNext()) {
//            Map.Entry<FilterItemBean, PopupBean> entry = it.next();
//            PopupBean mPopupBean = entry.getValue();
//            if (!mPopupBean.isNo) {
//                String key = entry.getKey().sqlName;
//                params.put(key, mPopupBean.name);
//            }
//        }
//
//        List<DeviceInfoBean> deviceList = DeviceInfoDAO.getInstance().getDeviceInfosByMaps(params);
//        contentList.clear();
//        contentList.addAll(deviceList);
//        contentAdapter.notifyDataSetChanged();
//        closePopupWindow(false);
//    }
//
//
//    @Subscriber(tag = "onFilterContentClick")
//    public void onFilterContentClick(DeviceInfoBean bean) {
//        Intent intent = new Intent();
//        intent.setClass(activity, DeviceDetailActivity.class);
//        intent.putExtra("param", bean);
//        activity.startActivity(intent);
//
//        isOepnNewDialog = false;
//        closePopupWindow(false);
//    }
//
//    private void showPopupWindow() {
//        for (FilterItemBean item : itemList) {
//            if (item.equals(currentFilterItemBean)) {
//                item.isSelected = true;
//            } else {
//                item.isSelected = false;
//            }
//        }
//        itemAdapter.notifyDataSetChanged();
//
//        popupMenuViews.setVisibility(View.VISIBLE);
//        popupMenuViews.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.dd_menu_in));
//        layout_mask.setVisibility(View.VISIBLE);
//        layout_mask.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.dd_mask_in));
//    }
//
//    private void closePopupWindow(boolean isOepnNewDialog) {
//        this.isOepnNewDialog = isOepnNewDialog;
//        for (FilterItemBean item : itemList) {
//            item.isSelected = false;
//        }
//        itemAdapter.notifyDataSetChanged();
//
//        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.dd_menu_out);
//        animation.setAnimationListener(this);
//
//        popupMenuViews.setVisibility(View.GONE);
//        popupMenuViews.setAnimation(animation);
//        layout_mask.setVisibility(View.GONE);
//        layout_mask.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.dd_mask_out));
//    }

//    private boolean isShowPopupWindow() {
//        return popupMenuViews.getVisibility() == View.VISIBLE;
//    }
//
//    @Subscriber(tag = "onRefreshDeviceList")
//    public void onRefreshDeviceList(String param) {
//        requestService();
//    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
//        if (isOepnNewDialog) {
//            showPopupWindow();
//        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

//    public boolean setKeyBackPress() {
//        if (isShowPopupWindow()) {
//            currentFilterItemBean = null;
//            closePopupWindow(false);
//            return false;
//        } else {
//            return true;
//        }
//    }

    @Override
    public void onClick(View v) {
//        currentFilterItemBean = null;
//        closePopupWindow(false);
    }

    @Override
    public void onRefresh() {
//        requestService();
    }
}
