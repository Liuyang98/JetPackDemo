package com.zintow.myjetpackdemo.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zintow.myjetpackdemo.MainActivity
import com.zintow.myjetpackdemo.config.App

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    protected open fun getAppViewModelProvider(mainActivity: MainActivity): ViewModelProvider? {
        return (applicationContext as App).getAppViewModelProvider(this)
    }

}