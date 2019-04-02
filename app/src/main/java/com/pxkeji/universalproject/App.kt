package com.pxkeji.universalproject

import android.app.Application
import com.pxkeji.util.LogUtil

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtil.level = LogUtil.DEBUG
    }
}