package com.ly.myjetpackdemo.activity

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.zintow.myjetpackdemo.R
import com.ly.myjetpackdemo.base.BaseActivity
import com.ly.myjetpackdemo.config.App
import com.ly.myjetpackdemo.util.AndroidUtil
import com.zintow.myjetpackdemo.databinding.ActivityMainBinding
import com.ly.myjetpackdemo.viewmodel.MainHomeViewModel
import java.lang.reflect.Method

class MainActivity : BaseActivity() {
    private val TAG: String = "MAIN_ACT"
    private lateinit var bind: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var vm: MainHomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        vm = (applicationContext as App).getAppViewModelProvider(this)
            .get(MainHomeViewModel::class.java)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow),
            bind.drawerLayout
        )
        bind.navView.setupWithNavController(findNavController(R.id.nav_host_fragment))
        bangCheck()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun bangCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getNotchParams()
        } else {
            domesticCheck()
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun getNotchParams() {
        Log.e(TAG, "getNotchParams")
        window.decorView.post {
            val windowInsets: WindowInsets = window.decorView.rootWindowInsets
            // 当全屏顶部显示黑边时，getDisplayCutout()返回为null
            val displayCutout = windowInsets.displayCutout
            if (displayCutout == null) {
                vm.stateBarTop.value=AndroidUtil.getStatusHeight(this)
                Log.e(TAG, "非刘海屏"+ vm.stateBarTop.value)
                return@post
            }
            Log.e(TAG, "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.safeInsetLeft)
            Log.e(TAG, "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.safeInsetRight)
            Log.e(TAG, "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.safeInsetTop)
            Log.e(TAG, "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.safeInsetBottom)
            vm.stateBarTop.value = displayCutout.safeInsetTop
            // 获得刘海区域
            val rects: List<Rect> = displayCutout.boundingRects
            if (rects.isEmpty()) {
                Log.e(TAG, "不是刘海屏")
            } else {
                Log.e("TAG", "刘海屏数量:" + rects.size)
                for (rect in rects) {
                    Log.e("TAG", "刘海屏区域：$rect")
                }
            }
        }
    }

    private fun domesticCheck() {
        Log.e(TAG, "domesticCheck")
        hasNotch(this)
    }

    /**
     * 华为刘海屏判断
     */
    private fun hasNotch(context: Context): Boolean {
        var ret = false
        try {
            val cl = context.classLoader
            val hwSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get: Method = hwSizeUtil.getMethod("hasNotchInScreen")
            ret = get.invoke(hwSizeUtil) as Boolean
        } catch (e: ClassNotFoundException) {
            Log.e("test", "hasNotchInScreen ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Log.e("test", "hasNotchInScreen NoSuchMethodException")
        } catch (e: Exception) {
            Log.e("test", "hasNotchInScreen Exception")
        } finally {
            return ret
        }
    }

}