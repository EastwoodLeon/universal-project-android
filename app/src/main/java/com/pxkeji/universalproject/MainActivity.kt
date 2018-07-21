package com.pxkeji.universalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pxkeji.ui.showToast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showToast(this, "abc")
    }
}
