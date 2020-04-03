package com.ly.myjetpackdemo

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ly.myjetpackdemo.base.BaseActivity
import com.ly.myjetpackdemo.bean.MainHomeBean
import com.ly.myjetpackdemo.config.App
import com.zintow.myjetpackdemo.databinding.ActivityDemoBinding
import com.ly.myjetpackdemo.viewmodel.MainHomeViewModel
import com.zintow.myjetpackdemo.R

class DemoActivity : BaseActivity() {
    private lateinit var bind: ActivityDemoBinding
    private lateinit var vm: MainHomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_demo)
        bind.lifecycleOwner = this
        vm = (applicationContext as App).getAppViewModelProvider(this).get(MainHomeViewModel::class.java)

        bind.imageView.setOnClickListener {
            val nb = MainHomeBean()
            nb.tip = "通过ViewModel修改Fragment中的数据"
            vm.liveData.value = nb
        }
    }
}
