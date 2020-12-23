package cn.drivingeasy.net;

import com.example.lib_retrofit_db.BuildConfig


class Contacts {
    companion object {
        val DEBUG: Boolean=true
        var APP_KEY: String = "${BuildConfig.APP_KEY}"//通用



        var BASEURL: String = "${BuildConfig.BASEURL}"


    }

}
