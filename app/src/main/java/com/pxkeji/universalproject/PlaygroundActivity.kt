package com.pxkeji.universalproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_playground.*

class PlaygroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playground)

        vs_1.onDone = {
            Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show()
        }

        btn_reset.setOnClickListener {
            vs_1.reset()
        }
    }
}