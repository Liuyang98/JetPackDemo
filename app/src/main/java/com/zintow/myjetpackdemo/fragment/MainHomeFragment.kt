package com.zintow.myjetpackdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zintow.myjetpackdemo.R
import com.zintow.myjetpackdemo.base.BaseFragment
import com.zintow.myjetpackdemo.databinding.FragmentMainHomeBinding
import com.zintow.myjetpackdemo.viewmodel.MainHomeViewModel

class MainHomeFragment : BaseFragment() {
    private lateinit var vm: MainHomeViewModel
    private lateinit var bind: FragmentMainHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = getFragmentViewModelProvider(this).get(MainHomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_main_home, container, false)
        bind = FragmentMainHomeBinding.bind(view)
        bind.vm=vm
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        vm.title.set("标题")
    }
}
