package com.pxkeji.universalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.pxkeji.ui.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetCount.setOnClickListener {
            val count = shopCartPlusMinus.currentCount
            Toast.makeText(this, "count: $count", Toast.LENGTH_SHORT).show()
        }

        btnSetCount.setOnClickListener {
            var count = 0
            try {

                count = editText2.text.toString().trim().toInt()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }


            shopCartPlusMinus.currentCount = count
        }
    }
}
