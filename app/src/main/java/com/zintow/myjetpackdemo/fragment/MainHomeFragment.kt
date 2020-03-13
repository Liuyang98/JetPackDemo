package com.zintow.myjetpackdemo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.zintow.myjetpackdemo.R
import com.zintow.myjetpackdemo.base.BaseFragment
import com.zintow.myjetpackdemo.bean.MainHomeBean
import com.zintow.myjetpackdemo.databinding.FragmentMainHomeBinding
import com.zintow.myjetpackdemo.viewmodel.MainHomeViewModel

//协程
class MainHomeFragment : BaseFragment(), View.OnClickListener {
    private val TAG = "DrawerFragment"
    private lateinit var vm: MainHomeViewModel
    private lateinit var bind: FragmentMainHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = getFragmentViewModelProvider(this).get(MainHomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)
        bind = FragmentMainHomeBinding.bind(view)
        bind.vm = vm
        bind.lifecycleOwner=this
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    //如何进行Activity-Fragment之间的通信
    private fun init() {
        vm.title.set("标题")
        val nb = MainHomeBean()
        nb.tip="tip"
        vm.liveData.value=nb
        vm.liveData.observe(viewLifecycleOwner, Observer<MainHomeBean> {
            Log.e(TAG, "数据发生改变:")
        })

        bind.btn.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        //TODO 编写LiveData用法
        val nb = MainHomeBean()
        nb.tip = "11223"
        vm.liveData.setValue(nb)


    }
}