package com.pxkeji.universalproject.net

import com.pxkeji.net.createRetrofit

class MyRetrofit private constructor(){
    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = createRetrofit("http://api.scedumedia.com/", 60, 60, 60, true)
    }


}