package com.pxkeji.ui.view

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet

import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.pxkeji.ui.R
import com.pxkeji.ui.showToast


/*
<com.pxkeji.ui.view.ShopCartPlusMinus
        android:id="@+id/shopCartPlusMinus2"
        android:layout_width="200dp"
        android:layout_height="48dp"
        android:layout_margin="20dp"
        app:scpm_buttonWidth="48dp"/>
 */
class ShopCartPlusMinus : LinearLayout {

    var currentCount: Int = 0
        get() {
            val count = getCountFromEditText()
            return count
        }
        set(value) {

            field = legalizeCount(value)
            editText.setText(field.toString())
        }

    private var mCount: Int = 1
    var minCount: Int = 1

    var maxCount: Int = 1000

    private lateinit var btnMinus: Button
    private lateinit var editText: EditText
    private lateinit var btnPlus: Button

    constructor(context: Context) : super(context) {
        inflateView()
        hookup()
    }



    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        inflateView()

        val ta = context.obtainStyledAttributes(attrs, R.styleable.ShopCartPlusMinus)

        val buttonWidthPx = ta.getDimension(R.styleable.ShopCartPlusMinus_scpm_buttonWidth, 10f)


        val minusBtnParams = LayoutParams(buttonWidthPx.toInt(), LayoutParams.MATCH_PARENT)
        btnMinus.layoutParams = minusBtnParams

        val plusBtnParams = LayoutParams(buttonWidthPx.toInt(), LayoutParams.MATCH_PARENT)
        btnPlus.layoutParams = plusBtnParams

        hookup()
    }

    private fun inflateView() {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        li.inflate(R.layout.shop_cart_plus_minus, this, true)

        btnMinus = findViewById(R.id.btnMinus)
        editText = findViewById(R.id.editText)
        btnPlus = findViewById(R.id.btnPlus)

        setMinusBtnTextColor()
    }

    private fun hookup() {
        btnMinus.setOnClickListener {
            var count = getCountFromEditText()

            mCount = --count

            checkMinus()


        }

        btnPlus.setOnClickListener {


            var count = getCountFromEditText()

            mCount = ++count;

            editText.setText(mCount.toString())

            setMinusBtnTextColor()
        }


    }

    private fun setMinusBtnTextColor() {
        if (mCount <= minCount) {
            btnMinus.setTextColor(ResourcesCompat.getColor(resources, R.color.shop_cart_plus_minus_inactive, null))
        } else {
            btnMinus.setTextColor(ResourcesCompat.getColor(resources, R.color.shop_cart_plus_minus_active, null))
        }
    }

    private fun checkMinus() {
        if (mCount < minCount) {
            showToast(context, "受不了了，宝贝不能再减少了哦")

            mCount = minCount
        }

        editText.setText(mCount.toString())

        setMinusBtnTextColor()
    }

    private fun getCountFromEditText() : Int {
        var count = 0

        try {
            count = editText.text.toString().trim().toInt()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        return count
    }

    private fun legalizeCount(count: Int) : Int = when {
        count < minCount -> minCount
        count > maxCount -> maxCount
        else -> count
    }
}