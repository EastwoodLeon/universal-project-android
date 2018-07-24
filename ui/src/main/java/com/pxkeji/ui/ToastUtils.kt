package com.pxkeji.ui

import android.content.Context
import android.widget.Toast

var toast: Toast? = null


fun showToast(context: Context, msg: String) {

    if (toast == null) {
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
    } else {
        toast?.setText(msg)
    }

    toast?.show()


}