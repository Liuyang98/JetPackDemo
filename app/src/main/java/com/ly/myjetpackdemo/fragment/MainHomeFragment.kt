package com.ly.myjetpackdemo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ly.myjetpackdemo.base.BaseFragment
import com.ly.myjetpackdemo.base.BasePagerAdapter
import com.ly.myjetpackdemo.bean.MainHomeBean
import com.ly.myjetpackdemo.ui.magictablayout.MagicIndictorHelper
import com.ly.myjetpackdemo.util.DensityUtil
import com.ly.myjetpackdemo.viewmodel.MainHomeViewModel
import com.zintow.myjetpackdemo.R
import com.zintow.myjetpackdemo.databinding.FragmentMainHomeBinding
import java.util.*

//协程
class MainHomeFragment : BaseFragment(), View.OnClickListener {
    private val TAG = "DrawerFragment"
    private val TITLES: Array<String?>? = arrayOf("全部", "文章", "视频")
    private lateinit var vm: MainHomeViewModel
    private lateinit var bind: FragmentMainHomeBinding
    private val fragments: MutableList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")
        vm = getFragmentViewModelProvider(this).get(MainHomeViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)
        bind = FragmentMainHomeBinding.bind(view)
        bind.vm = vm
        bind.lifecycleOwner = this
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initFragment()
    }

    private fun initFragment() {
        fragments.add(IndexFragment())
        fragments.add(GalleryFragment())
        fragments.add(GalleryFragment())
        MagicIndictorHelper.init(mActivity, TITLES, bind.magicTabLayout, bind.vp)
        bind.vp.adapter = fragmentManager?.let { BasePagerAdapter(it, fragments) }
    }

    //如何进行Activity-Fragment之间的通信
    private fun init() {
        vm.title.set("标题")
        vm.liveData.value = MainHomeBean("tip")
        vm.liveData.observe(viewLifecycleOwner, Observer { Log.e(TAG, "数据发生改变:") })
        vm.stateBarTop.observe(viewLifecycleOwner, Observer {
            changeTitle(it)
        })
    }

    private fun changeTitle(stateTop: Int) {
        val titleHeight = DensityUtil.dp2px(150F) + stateTop;
        bind.appBar.layoutParams.height = titleHeight
        val lp: CoordinatorLayout.LayoutParams =
                bind.constraintLayout.layoutParams as CoordinatorLayout.LayoutParams
        lp.setMargins(0, titleHeight, 0, 0)
        bind.constraintLayout.layoutParams = lp
    }

    override fun onClick(v: View?) {
    }

}