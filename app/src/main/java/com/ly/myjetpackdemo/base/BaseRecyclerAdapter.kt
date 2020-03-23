package com.ly.myjetpackdemo.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by yangl.liu on 2017/3/2.
 * 基础的RecyclerView适配
 */
abstract class BaseRecyclerAdapter<T : RecyclerView.ViewHolder>(private var mContext: Context, protected var mDatas: List<Any>) : RecyclerView.Adapter<T>() {
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T
    abstract override fun onBindViewHolder(holder: T, position: Int)

    override fun getItemCount(): Int {
        return mDatas.size
    }

    /**
     * 生成View布局
     */
    protected fun initView(parent: ViewGroup, rid: Int): View {
        return LayoutInflater.from(mContext).inflate(rid, parent, false)
    }

}