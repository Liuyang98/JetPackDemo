package com.ly.myjetpackdemo.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ly.myjetpackdemo.config.App

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