package com.zintow.myjetpackdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zintow.myjetpackdemo.base.BaseFragment
import com.zintow.myjetpackdemo.viewmodel.MainHomeViewModel

class MainHomeFragment : BaseFragment() {
    lateinit var vm: MainHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm = getFragmentViewModelProvider(this).get(MainHomeViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
