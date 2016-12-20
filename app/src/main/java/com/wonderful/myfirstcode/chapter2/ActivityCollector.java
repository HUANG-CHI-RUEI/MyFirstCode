package com.wonderful.myfirstcode.chapter2;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动管理器
 * Created by KXwon on 2016/12/9.
 */

public class ActivityCollector {
    // 通过一个List来缓存活动
    public static List<Activity> activities = new ArrayList<Activity>();

    /**
     * 用于向List中添加一个活动
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 用于从List中移除活动
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 将List中存储的活动全部销毁掉
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
