package com.ly.myjetpackdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.zintow.myjetpackdemo.R
import com.ly.myjetpackdemo.base.BaseFragment
import com.ly.myjetpackdemo.viewmodel.IndexViewModel

class GalleryFragment : BaseFragment() {

    private lateinit var indexViewModel: IndexViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        indexViewModel = getFragmentViewModelProvider(this).get(IndexViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        indexViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })
        return root
    }
}
