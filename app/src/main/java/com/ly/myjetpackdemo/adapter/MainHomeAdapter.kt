package com.ly.myjetpackdemo.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.bind
import androidx.recyclerview.widget.RecyclerView
import com.ly.myjetpackdemo.base.BaseRecyclerAdapter
import com.ly.myjetpackdemo.bean.CityEntity
import com.ly.myjetpackdemo.util.glide.GlideUtil
import com.zintow.myjetpackdemo.R
import com.zintow.myjetpackdemo.databinding.ItemMainHomeBinding

/**
 * 城市列表（一级） 适配器
 */
class MainHomeAdapter(mContext: Context, mDatas: List<Any>) : BaseRecyclerAdapter<RecyclerView.ViewHolder>(mContext, mDatas) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FriendViewHolder(initView(parent, R.layout.item_main_home))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindMediaViewHolder(holder as FriendViewHolder, mDatas[position] as CityEntity)
    }

    private fun bindMediaViewHolder(holder: FriendViewHolder, entity: CityEntity) {
        holder.bind.tv.text = entity.title
        holder.bind.entity = entity
        GlideUtil.setImageView(holder.bind.iv,entity.imgUrl)
    }

    private inner class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var bind: ItemMainHomeBinding = bind(itemView)!!
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {

        }
    }
}