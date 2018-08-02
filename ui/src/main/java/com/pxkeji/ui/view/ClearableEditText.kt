package com.pxkeji.ui.view

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.pxkeji.ui.R
import com.pxkeji.ui.util.px2sp

/*
<com.pxkeji.ui.view.ClearableEditText
            android:layout_width="match_parent"
            android:layout_height="50dp"
            app:cet_editTextPadding="10dp"
            app:cet_buttonWidth="48dp"
            app:cet_buttonPadding="8dp"
            app:cet_inputType="1"
            app:cet_textColor="@android:color/black"
            app:cet_textSize="19sp"
            app:cet_buttonSrc="@drawable/icon_delete_light"/>
 */

class ClearableEditText  : LinearLayout {


    private lateinit var editText: EditText

    private lateinit var clearButton: ImageView

    constructor(context: Context) : super(context) {
        inflateView()
        hookup()
    }



    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        inflateView()

        val ta = context.obtainStyledAttributes(attrs, R.styleable.ClearableEditText)

        val inputType = ta.getInteger(R.styleable.ClearableEditText_cet_inputType, 1)
        val textColor = ta.getColor(R.styleable.ClearableEditText_cet_textColor, 0)
        val textSizePx = ta.getDimension(R.styleable.ClearableEditText_cet_textSize, 20f)
        val editTextPadding = ta.getDimension(R.styleable.ClearableEditText_cet_editTextPadding, 0f)
        val cetBtnWidth = ta.getDimension(R.styleable.ClearableEditText_cet_buttonWidth, 48f)
        val cetBtnPadding = ta.getDimension(R.styleable.ClearableEditText_cet_buttonPadding, 8f)
        val btnSrc = ta.getDrawable(R.styleable.ClearableEditText_cet_buttonSrc)



        ta.recycle()


        editText.inputType = when(inputType) {
            2 -> {

                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            }
            else -> {

                InputType.TYPE_CLASS_TEXT
            }

        }

        editText.setTextColor(textColor)

        editText.textSize = px2sp(context, textSizePx).toFloat()


        editText.setPadding(editTextPadding.toInt(), editTextPadding.toInt(), editTextPadding.toInt(), editTextPadding.toInt())
        val clearButtonParams = LayoutParams(cetBtnWidth.toInt(), LayoutParams.MATCH_PARENT)
        clearButton.layoutParams = clearButtonParams

        clearButton.setPadding(cetBtnPadding.toInt(), cetBtnPadding.toInt(), cetBtnPadding.toInt(), cetBtnPadding.toInt())

        if (btnSrc != null) {
            clearButton.setImageDrawable(btnSrc)
        }


        hookup()
    }

    private fun inflateView() {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        li.inflate(R.layout.clearable_edit_text, this, true)

        editText = findViewById(R.id.editText)

        clearButton = findViewById(R.id.clearButton)
    }

    private fun hookup() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                when{
                    p0 == null || p0.toString() == "" -> clearButton.visibility = View.INVISIBLE
                    else -> clearButton.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        editText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                if (editText.text.toString() != "") {
                    clearButton.visibility = View.VISIBLE
                } else {
                    clearButton.visibility = View.INVISIBLE
                }
            } else {
                clearButton.visibility = View.INVISIBLE
            }
        }

        clearButton.setOnClickListener {
            editText.setText("")
        }
    }
}