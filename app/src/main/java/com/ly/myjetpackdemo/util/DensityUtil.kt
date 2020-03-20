package com.ly.myjetpackdemo.util

import android.util.DisplayMetrics
import com.ly.myjetpackdemo.config.App.StaticParams.context

object DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px(dpValue: Float): Int {
        val scale = displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(pxValue: Float): Int {
        val scale = displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * （DisplayMetrics类中属性scaledDensity）
     */
    fun px2sp(pxValue: Float): Int {
        val fontScale = displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * （DisplayMetrics类中属性scaledDensity）
     */
    fun sp2px(spValue: Float): Int {
        val fontScale = displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    private val displayMetrics: DisplayMetrics get() = context.resources.displayMetrics
}