package com.ly.myjetpackdemo.util.glide

import android.content.res.Resources
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest
import kotlin.math.roundToInt

/**
 * Glide 圆角
 */
class GlideRoundTransform(dp: Int) : BitmapTransformation() {

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        return roundCrop(pool, toTransform)
    }

    val id: String
        get() = javaClass.name + radius.roundToInt()

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {}

    companion object {
        private var radius = 0f
        private fun roundCrop(pool: BitmapPool, source: Bitmap): Bitmap {
            var result: Bitmap? = pool[source.width, source.height, Bitmap.Config.ARGB_8888]
            if (result == null) {
                result = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
            }
            val canvas = Canvas(result!!)
            val paint = Paint()
            paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            paint.isAntiAlias = true
            val rectF = RectF(0f, 0f, source.width.toFloat(), source.height.toFloat())
            canvas.drawRoundRect(rectF, radius, radius, paint)
            return result
        }
    }

    init {
        radius = Resources.getSystem().displayMetrics.density * dp
    }
}