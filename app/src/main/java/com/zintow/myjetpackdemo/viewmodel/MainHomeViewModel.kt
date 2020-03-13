package com.zintow.myjetpackdemo.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zintow.myjetpackdemo.bean.MainHomeBean


/**
 * TODO tip：每个页面都要单独准备一个 stateViewModel，
 * 来托管 DataBinding 绑定的临时状态，以及视图控制器重建时状态的恢复。
 *
 * 此外，stateViewModel 的职责仅限于 状态托管，不建议在此处理 UI 逻辑，
 * UI 逻辑只适合在 Activity/Fragment 等视图控制器中完成，是 “数据驱动” 的一部分，
 * 将来升级到 Jetpack Compose 更是如此。
 * <p>
 * Create by KunMinX at 19/10/29
 */
class MainHomeViewModel:ViewModel() {
    var hasUser:ObservableBoolean=ObservableBoolean()
    var title: ObservableField<String> =ObservableField()
    var liveData: MutableLiveData<MainHomeBean> =MutableLiveData()

}