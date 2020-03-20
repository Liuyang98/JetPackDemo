package com.ly.myjetpackdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.zintow.myjetpackdemo.R
import com.ly.myjetpackdemo.base.BaseFragment
import com.ly.myjetpackdemo.viewmodel.IndexViewModel
import com.zintow.myjetpackdemo.databinding.FragmentIndexBinding

class IndexFragment : BaseFragment() {
    private lateinit var indexViewModel: IndexViewModel
    private lateinit var bind: FragmentIndexBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        indexViewModel = getFragmentViewModelProvider(this).get(IndexViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_index, container, false)
        bind= FragmentIndexBinding.bind(root)
        init()
        return root
    }

    private fun init() {
        indexViewModel.text.observe(viewLifecycleOwner, Observer {
//            bind.textIndex.text = it
        })

    }
}
