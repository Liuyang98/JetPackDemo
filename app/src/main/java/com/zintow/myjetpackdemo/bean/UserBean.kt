package com.zintow.myjetpackdemo.bean

import com.zintow.myjetpackdemo.base.BaseBean

class UserBean : BaseBean() {
    var id: Long = -1
    var name: String = ""
    var headUrl: String = ""


    override fun printInfo() {
        super.printInfo()
    }

}