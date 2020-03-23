package com.ly.myjetpackdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ly.myjetpackdemo.adapter.CityAdapter
import com.ly.myjetpackdemo.base.BaseFragment
import com.ly.myjetpackdemo.bean.CityEntity
import com.ly.myjetpackdemo.viewmodel.IndexViewModel
import com.zintow.myjetpackdemo.R
import com.zintow.myjetpackdemo.databinding.FragmentIndexBinding

class IndexFragment : BaseFragment() {
    private lateinit var indexViewModel: IndexViewModel
    private lateinit var adapter: CityAdapter
    private lateinit var bind: FragmentIndexBinding
    private val datas: MutableList<CityEntity> =ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        indexViewModel = getFragmentViewModelProvider(this).get(IndexViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_index, container, false)
        bind = FragmentIndexBinding.bind(root)
        init()
        return root
    }

    private fun init() {
        for (i in 0..9) {
            datas.add(CityEntity("标题：$i"))
        }
        adapter= CityAdapter(mActivity, datas)
        bind.rv.layoutManager=LinearLayoutManager(mActivity)
        bind.rv.adapter = adapter
    }
}
