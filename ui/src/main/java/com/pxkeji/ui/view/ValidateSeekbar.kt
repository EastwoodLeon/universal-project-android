package com.pxkeji.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.SeekBar
import android.widget.TextView
import com.pxkeji.ui.R

class ValidateSeekbar @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "ValidateSeekbar"
    }

    private val seekBar: SeekBar
    private val textView: TextView

    init {
        inflate(context, R.layout.validate_seekbar, this)
        seekBar = findViewById(R.id.sb)
        textView = findViewById(R.id.tv)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.ValidateSeekbar)

        val text = ta.getString(R.styleable.ValidateSeekbar_vs_text) ?: "请向右滑动"
        val textColor = ta.getColor(R.styleable.ValidateSeekbar_vs_text_color, 0x00000000)
        val textSize = ta.getDimension(R.styleable.ValidateSeekbar_vs_text_size, 12f)


        textView.text = text
        textView.setTextColor(textColor)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)

        ta.recycle()
    }
}