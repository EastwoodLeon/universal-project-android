package com.pxkeji.eentertainment.ui.common.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.DialogFragment
import android.view.Gravity
import android.view.WindowManager
import com.pxkeji.universalproject.R


open class BaseDialog : DialogFragment() {

    override fun onStart() {
        super.onStart()
        val window = dialog.window
        val params = window.attributes
        params.gravity = Gravity.BOTTOM
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = params
        window.setWindowAnimations(R.style.anim_panel_up_from_bottom)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}