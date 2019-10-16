package com.example.setupwizarddemo.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;

import com.example.setupwizarddemo.MainActivity;

import java.util.Stack;

public class AppManager {

        private static Stack<Activity> activityStack = new Stack<Activity>();

        /**
         * 添加Activity到堆栈
         */
        public static void addActivity(Activity activity) {
            activityStack.push(activity);
        }

        /**
         * 获取当前Activity（堆栈中最后一个压入的）
         */
        public static Activity currentActivity() {
            return activityStack.lastElement();
        }

        /**
         * 结束当前Activity（堆栈中最后一个压入的）
         */
        public static void finishCurrentActivity() {
            Activity activity = activityStack.pop();
            activity.finish();
        }

        /**
         * 结束指定的Activity
         */
        public static void finishActivity(Activity activity) {
            if (activity != null) {
                activityStack.remove(activity);
                if(!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }

        /**
         * 结束指定类名的Activity
         */
        public static void finishActivity(Class<?> cls) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        }

        /**
         * 结束所有Activity
         */
        public static void finishAllActivity() {
            for (Activity activity : activityStack) {
                if (activity != null) {
                    activity.finish();
                }
            }
            activityStack.clear();
        }

        /**
         * 退出应用程序
         */
        public static void AppExit(Context context) {
            try {
                finishAllActivity();
                ActivityManager manager = (ActivityManager) context
                        .getSystemService(Context.ACTIVITY_SERVICE);
                manager.killBackgroundProcesses(context.getPackageName());
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void selfDisable(Context context){
            final String USER_SETUP_COMPLETE = "user_setup_complete";
            // Add a persistent setting to allow other apps to know the device has been provisioned.
            Settings.Global.putInt(context.getContentResolver(), Settings.Global.DEVICE_PROVISIONED, 1);
            Settings.Secure.putInt(context.getContentResolver(), USER_SETUP_COMPLETE, 1);

            // remove this activity from the package manager.
            PackageManager pm = context.getPackageManager();
            ComponentName name = new ComponentName(context, MainActivity.class);
            pm.setComponentEnabledSetting(name, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);

        }

    }
