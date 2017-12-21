package com.jiayuan.huawei.hwframeworklib.ui.customview.moreView;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class MoreViewResultBean {
    public static final int RESULT_TYPE_NORMAL=0;
    public static final int RESULT_TYPE_NONE=1;
    public static final int RESULT_TYPE_ERROR=2;

    public List<?> result=null;
    public int resultType=RESULT_TYPE_NORMAL;

}
