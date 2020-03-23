package com.ly.myjetpackdemo.bean

class CityEntity {
    private lateinit var value: String

    fun getValue(): String {
        return value
    }

    fun setValue(value: String) {
        this.value = value
    }


    constructor() : super()

    constructor(value: String) : super() {
        this.value = value
    }

}