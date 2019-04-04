package com.pxkeji.universalproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_vs_test.*

class VsTestActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_test)

        sb.apply {
            setPadding(0, 0, 0, 0)
            setOnSeekBarChangeListener(this@VsTestActivity)
        }
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, p2: Boolean) {

        if (progress == seekBar.max) {

        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        if (seekBar.progress != seekBar.max) {
            seekBar.progress = 0
        }
    }
}