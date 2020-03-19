package com.ly.myjetpackdemo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import com.zintow.myjetpackdemo.R
import com.ly.myjetpackdemo.base.BaseFragment
import com.ly.myjetpackdemo.bean.MainHomeBean
import com.zintow.myjetpackdemo.databinding.FragmentMainHomeBinding
import com.ly.myjetpackdemo.viewmodel.MainHomeViewModel

//协程
class MainHomeFragment : BaseFragment(), View.OnClickListener {
    private val TAG = "DrawerFragment"
    private lateinit var vm: MainHomeViewModel
    private lateinit var bind: FragmentMainHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG,"onCreate")
        vm = getFragmentViewModelProvider(this).get(MainHomeViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)
        bind = FragmentMainHomeBinding.bind(view)
        bind.vm = vm
        bind.lifecycleOwner = this
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    //如何进行Activity-Fragment之间的通信
    private fun init() {
        bind.btn.setOnClickListener(this)
        vm.title.set("标题")
//        vm.liveData.value = MainHomeBean("tip")
        vm.liveData.observe(viewLifecycleOwner, Observer { Log.e(TAG, "数据发生改变:") })
        vm.stateBarTop.observe(viewLifecycleOwner, Observer {
            changeTitle(it)
        })
    }

    private fun changeTitle(stateTop: Int) {
        val lp: FrameLayout.LayoutParams = bind.coorLayout.layoutParams as FrameLayout.LayoutParams
        lp.setMargins(0, stateTop, 0, 0)
        bind.coorLayout.layoutParams = lp
    }

    override fun onClick(v: View?) {
        vm.liveData.value = MainHomeBean("11223")
    }

}