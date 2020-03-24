package com.ly.myjetpackdemo.util.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Created by yangl.liu on 2016/10/31.
 */
object GlideUtil {
    private const val DEFAULT_RADIO = 3

    /**
     * 加载网络图片_基础
     */
    fun setImageView(imageView: ImageView, url: String?) {
        val options = RequestOptions()
        Glide.with(imageView.context)
                .load(url)
                .apply(options)
                .into(imageView)
    }

    /**
     * 加载网络图片_自定义圆角
     */
    fun setRoundImageView(imageView: ImageView, url: String?) {
        val options = RequestOptions()
                .override(imageView.width, imageView.height)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(GlideRoundTransformCenterCrop(DEFAULT_RADIO))
        Glide.with(imageView.context)
                .load(url)
                .apply(options)
                .into(imageView)
    }

    fun setRoundImageViewAuto(
            imageView: ImageView,
            url: String?,
            width: Int,
            height: Int
    ) {
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        val options = RequestOptions()
                .override(width, height)
                .transform(GlideRoundTransformCenterCrop(DEFAULT_RADIO))
        Glide.with(imageView.context)
                .load(url)
                .apply(options)
                .into(imageView)
    }

    fun setCircleImageView(imageView: ImageView, url: Int) {
        val options = RequestOptions()
                .transform(GlideCircleTransform())
        Glide.with(imageView.context)
                .load(url)
                .apply(options)
                .into(imageView)
    }

    /**
     * 加载网络图片_圆形
     */
    fun setLocalCircleImageView(
            imageView: ImageView,
            url: String?
    ) {
        val options = RequestOptions()
                .transform(GlideCircleTransform())
        Glide.with(imageView.context)
                .load(url)
                .apply(options)
                .into(imageView)
    }

    /**
     * 加载本地图片
     */
    fun setLocalImageView(imageView: ImageView, url: Int) {
        Glide.with(imageView.context)
                .load(url)
                .into(imageView)
    }

    /**
     * 加载本地图片
     */
    fun setLocalImageView(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
                .load(url)
                .into(imageView)
    }
}