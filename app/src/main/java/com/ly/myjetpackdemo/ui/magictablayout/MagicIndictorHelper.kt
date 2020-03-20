package com.ly.myjetpackdemo.ui.magictablayout

import android.content.Context
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.ly.myjetpackdemo.util.DensityUtil.dp2px
import com.zintow.myjetpackdemo.R
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

object MagicIndictorHelper {
    fun init(context: Context, titles: Array<String?>?, magicIndicator: MagicIndicator, viewPager: ViewPager): CommonNavigator {
        val commonNavigator = CommonNavigator(context)
        commonNavigator.isAdjustMode = true // 自适应模式
        commonNavigator.isSkimOver = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView: SimplePagerTitleView = ThreePagerTitleView(context)
                simplePagerTitleView.text = titles!![index]
                simplePagerTitleView.textSize = 17f
                simplePagerTitleView.normalColor = -0x666667
                simplePagerTitleView.width = dp2px(80f)
                simplePagerTitleView.selectedColor = -0xcccccd
                simplePagerTitleView.setOnClickListener { viewPager.currentItem = index }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.setColors(-0xdad98)
                indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                return indicator
            }

            override fun getCount(): Int {
                return titles?.size ?: 0
            }
        }

        magicIndicator.navigator = commonNavigator
        val titleContainer = commonNavigator.titleContainer // must after setNavigator
        titleContainer.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        titleContainer.dividerPadding = UIUtil.dip2px(context, 14.0)
        titleContainer.dividerDrawable = context.resources.getDrawable(R.drawable.simple_splitter)
        ViewPagerHelper.bind(magicIndicator, viewPager)
        return commonNavigator
    }

}