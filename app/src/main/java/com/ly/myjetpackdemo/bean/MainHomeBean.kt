package com.ly.myjetpackdemo.bean

import com.ly.myjetpackdemo.base.BaseBean

class MainHomeBean : BaseBean{
    var time: Long = 1L
    var content: String = ""
    var tip: String = ""


    constructor() : super()

    constructor(tip: String) : super() {
        this.tip = tip
    }

    constructor(time: Long, content: String, tip: String) : super() {
        this.time = time
        this.content = content
        this.tip = tip
    }


}