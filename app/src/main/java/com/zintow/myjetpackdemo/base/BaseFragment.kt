package com.zintow.myjetpackdemo.base

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zintow.myjetpackdemo.config.App

open class BaseFragment : Fragment() {
    lateinit var mActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

   open fun getFragmentViewModelProvider(fragment: Fragment): ViewModelProvider {
        return  (mActivity.applicationContext as App).getAppViewModelProvider(mActivity)
    }
}