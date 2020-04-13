package com.ly.myjetpackdemo.util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Rect
import android.net.ConnectivityManager
import android.os.Build
import android.os.Process
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.ly.myjetpackdemo.base.BaseActivity
import com.ly.myjetpackdemo.config.App
import java.util.*

/**
 * 与Android系统相关的工具类
 * Created by liuyang on 2016/3/3.
 */
object AndroidUtil {
    private var versionName: String? = null
    private var statusHeight = 0
    private var uuid: String? = null

    /**
     * 获取系统版本号 android 4.4
     */
    val systemVersion: String
        get() = Build.VERSION.RELEASE//

    /**
     * 获取手机型号
     */
    val phoneType: String
        get() = Build.MODEL //

    /**
     * 获取手机厂商
     *
     * @return
     */
    val vendor: String
        get() = Build.BRAND

    /**
     * 获取SDK版本
     */
    val sDKVersion: Int
        get() = Build.VERSION.SDK_INT

    private var widthPixels = 0

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(context: Context): Int {
        if (widthPixels == 0) {
            val wm = context.applicationContext
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            wm.defaultDisplay.getMetrics(dm)
            widthPixels = dm.widthPixels
        }
        return widthPixels
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHeight(context: Context): Int {
        val wm = context.applicationContext
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm.heightPixels
    }

    /**
     * 获取状态栏高度
     */
    fun getStatusHeight(activity: Activity): Int {
        if (statusHeight != 0) {
            return statusHeight
        }
        val localRect = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(localRect)
        statusHeight = localRect.top
        if (0 == statusHeight) {
            val localClass: Class<*>
            try {
                localClass = Class.forName("com.android.internal.R\$dimen")
                val localObject = localClass.newInstance()
                val i5 =
                    localClass.getField("status_bar_height")[localObject].toString().toInt()
                statusHeight = activity.resources.getDimensionPixelSize(i5)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return statusHeight
    }

    fun getCurProcessName(context: Context): String {
        val pid = Process.myPid()
        val activityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val l =
            activityManager.runningAppProcesses
        if (l != null) for (appProcess in l) {
            if (appProcess.pid == pid) {
                return appProcess.processName
            }
        }
        return ""
    }

    val packageInfo: PackageInfo?
        get() {
            val context: Context = App.context
            var pi: PackageInfo? = null
            try {
                val pm = context.packageManager
                pi = pm.getPackageInfo(context.packageName, PackageManager.GET_CONFIGURATIONS)
                return pi
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return pi
        }

    fun hideKeyBorad(activity: BaseActivity) {
        // 先隐藏键盘
        (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(
                activity.currentFocus?.windowToken
                , InputMethodManager.HIDE_NOT_ALWAYS
            )
        //接下来在这里做你自己想要做的事，实现自己的业务。
    }

    fun gpuSpeedUp(window: Window) {
        try {
            if (Build.VERSION.SDK.toInt() >= 11) {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
                )
            }
        } catch (e: Exception) {
        }
    }

    /**
     * 判断网络是否连接
     */
    fun checkNet(): Boolean {
        var hasNet = false
        val context: Context = App.context
        val connManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // 获取代表联网状态的NetWorkInfo对象
        val networkInfo = connManager.activeNetworkInfo
        // 获取当前的网络连接是否可用
        if (networkInfo != null) {
            hasNet = networkInfo.isAvailable
        }
        return hasNet
    }

    fun showErrorToast(mContext: Context) {
        if (checkNet()) {
            Toast.makeText(mContext, "无网络连接", Toast.LENGTH_SHORT).show()
        } else {  // 网络可用的情况下，提示语变更（服务器异常 ）
            Toast.makeText(mContext, "服务器异常", Toast.LENGTH_SHORT).show()
        }
    }


    @Synchronized
    fun getVersionName(context: Context): String? {
        if (versionName != null) {
            return versionName
        }
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                context.packageName, 0
            )
            versionName = packageInfo.versionName
            return versionName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}