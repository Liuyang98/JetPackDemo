package com.zintow.myjetpackdemo.activity

import android.annotation.TargetApi
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
import com.zintow.myjetpackdemo.base.BaseActivity
import com.zintow.myjetpackdemo.databinding.ActivityMainBinding
import java.lang.reflect.Method


class MainActivity : BaseActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow),
            bind.drawerLayout
        )
        bind.navView.setupWithNavController(navController)
        bangCheck()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun bangCheck() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getNotchParams()
        }else{
            domesticCheck()
        }

    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun getNotchParams() {
        window.decorView.post(Runnable {
            val windowInsets: WindowInsets = window.decorView.getRootWindowInsets()
            // 当全屏顶部显示黑边时，getDisplayCutout()返回为null
            val displayCutout = windowInsets.displayCutout
            Log.e("TAG", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout!!.safeInsetLeft)
            Log.e("TAG", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.safeInsetRight)
            Log.e("TAG", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.safeInsetTop)
            Log.e("TAG", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.safeInsetBottom)
            // 获得刘海区域
            val rects: List<Rect>? = displayCutout.boundingRects
            if (rects == null || rects.isEmpty()) {
                Log.e("TAG", "不是刘海屏")
            } else {
                Log.e("TAG", "刘海屏数量:" + rects.size)
                for (rect in rects) {
                    Log.e("TAG", "刘海屏区域：$rect")
                }
            }
        })
    }

    private fun domesticCheck() {

    }

    fun hasNotch(context: Context): Boolean {
        var ret = false
        try {
            val cl = context.classLoader
            val HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get: Method = HwNotchSizeUtil.getMethod("hasNotchInScreen")
            ret = get.invoke(HwNotchSizeUtil) as Boolean
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