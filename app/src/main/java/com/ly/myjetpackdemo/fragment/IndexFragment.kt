package com.ly.myjetpackdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ly.myjetpackdemo.adapter.MainHomeAdapter
import com.ly.myjetpackdemo.base.BaseFragment
import com.ly.myjetpackdemo.bean.CityEntity
import com.ly.myjetpackdemo.viewmodel.IndexViewModel
import com.zintow.myjetpackdemo.R
import com.zintow.myjetpackdemo.databinding.FragmentIndexBinding

class IndexFragment : BaseFragment() {
    private lateinit var indexViewModel: IndexViewModel
    private lateinit var adapter: MainHomeAdapter
    private lateinit var bind: FragmentIndexBinding
    private val datas: MutableList<CityEntity> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        indexViewModel = getFragmentViewModelProvider(this).get(IndexViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_index, container, false)
        bind = FragmentIndexBinding.bind(root)
        init()
        return root
    }

    private fun init() {
        val imgUrl = arrayOf(
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/b9489613a277477496c5bc94f4b34566.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/d62b0429e7a942819eccf57b541cd09d.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/332ce5d2d9994ee191d6c29cdbb10cca.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/10264e9f3c3a40e9995bf775a3ea7ac0.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/1951dda5577046e3b1d822627a9097b0.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/50623a6b8ab04a958a480313bcce801f.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/0dadb0de1efd46eca10e92e02ca3ff65.jpg",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/778136a44b9d42f69aa9a1da37ea1eb7.jpg",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/fefbc77a215d468e96264c14a02c77b0.jpg",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/4ee55c53d8224b74829c107f11042e32.jpg"
                ,
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/b9489613a277477496c5bc94f4b34566.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/d62b0429e7a942819eccf57b541cd09d.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/332ce5d2d9994ee191d6c29cdbb10cca.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/10264e9f3c3a40e9995bf775a3ea7ac0.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/1951dda5577046e3b1d822627a9097b0.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/50623a6b8ab04a958a480313bcce801f.png",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/0dadb0de1efd46eca10e92e02ca3ff65.jpg",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/778136a44b9d42f69aa9a1da37ea1eb7.jpg",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/fefbc77a215d468e96264c14a02c77b0.jpg",
                "https://zintow-mini-apps.oss-cn-hangzhou.aliyuncs.com/pzp/4ee55c53d8224b74829c107f11042e32.jpg"
        )
        for (i in 0..18) {
            datas.add(CityEntity("标题：$i", imgUrl[i]))
        }
        adapter = MainHomeAdapter(mActivity, datas)
        bind.rv.layoutManager = LinearLayoutManager(mActivity)
        bind.rv.adapter = adapter
    }
}
