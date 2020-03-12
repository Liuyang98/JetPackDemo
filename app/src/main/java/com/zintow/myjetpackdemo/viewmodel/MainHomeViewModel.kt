package com.zintow.myjetpackdemo.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MainHomeViewModel:ViewModel() {
    var hasUser:ObservableBoolean=ObservableBoolean()
    var title: ObservableField<String> =ObservableField()

}