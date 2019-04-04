package com.pxkeji.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.SeekBar
import android.widget.TextView
import com.pxkeji.ui.R

/**
 * <com.pxkeji.ui.view.ValidateSeekbar
        android:id="@+id/vs_1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="8dp"
        app:vs_text="123"
        app:vs_text_color="@android:color/holo_orange_light"
        app:vs_text_size="16sp"
        app:vs_progress_drawable="@drawable/vs_progress_drawable_test"
        app:vs_thumb="@drawable/vs_thumb"/>
 */

class ValidateSeekbar @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), SeekBar.OnSeekBarChangeListener {


    companion object {
        private const val TAG = "ValidateSeekbar"
    }

    private val seekBar: SeekBar
    private val textView: TextView
    var onDone: (() -> Unit)? = null

    init {
        inflate(context, R.layout.validate_seekbar, this)
        seekBar = findViewById(R.id.vs_sb)
        textView = findViewById(R.id.vs_tv)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.ValidateSeekbar)

        val text = ta.getString(R.styleable.ValidateSeekbar_vs_text) ?: "请向右滑动"
        val textColor = ta.getColor(R.styleable.ValidateSeekbar_vs_text_color, 0x00000000)
        val textSize = ta.getDimension(R.styleable.ValidateSeekbar_vs_text_size, 12f)
        val progressDrawable = ta.getDrawable(R.styleable.ValidateSeekbar_vs_progress_drawable)
        val thumb = ta.getDrawable(R.styleable.ValidateSeekbar_vs_thumb)

        textView.apply {
            this.text = text
            setTextColor(textColor)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        }

        seekBar.apply {
            this.progressDrawable = progressDrawable
            this.thumb = thumb
            thumbOffset = -2
            setPadding(0, 0, 0, 0)
            setOnSeekBarChangeListener(this@ValidateSeekbar)
        }



        ta.recycle()
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, p2: Boolean) {

        if (progress == seekBar.max) {
            onDone?.invoke()
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

        if (seekBar.progress != seekBar.max) {
            seekBar.progress = 0
        }
    }

    fun reset() {
        seekBar.progress = 0
    }
}