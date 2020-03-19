package com.ly.myjetpackdemo.config

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class App : Application(), ViewModelStoreOwner {
    private lateinit var context: Context
    private lateinit var vmStore: ViewModelStore
    private var mFactory: ViewModelProvider.Factory? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        vmStore = ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return vmStore
    }


    fun getAppViewModelProvider(activity: Activity): ViewModelProvider {
        return ViewModelProvider(this, getAppFactory(activity))
    }

    private fun getAppFactory(activity: Activity): ViewModelProvider.Factory {
        val application: Application = checkApplication(activity)
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        }
        return mFactory as ViewModelProvider.Factory
    }


    private fun checkApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException(
                "Your activity/fragment is not yet attached to Application. You can't request ViewModel before onCreate call."
            )
    }

    private fun checkActivity(fragment: Fragment): Activity {
        return fragment.activity
            ?: throw java.lang.IllegalStateException("Can't create ViewModelProvider for detached fragment")
    }

}