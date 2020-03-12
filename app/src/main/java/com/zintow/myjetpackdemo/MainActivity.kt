package com.zintow.myjetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zintow.myjetpackdemo.bean.UserBean
import com.zintow.myjetpackdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
    }

    private fun init() {
        val user = UserBean()
        user.name="姓名"
        bind.user=user
        applicationContext
        application
    }
}
