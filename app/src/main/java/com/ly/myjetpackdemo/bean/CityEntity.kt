package com.ly.myjetpackdemo.bean

class CityEntity {
    lateinit var title: String
    lateinit var imgUrl: String

    constructor() : super()

    constructor(title: String, imgUrl: String) {
        this.title = title
        this.imgUrl = imgUrl
    }

}