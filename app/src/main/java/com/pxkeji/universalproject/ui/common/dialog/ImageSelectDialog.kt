package com.pxkeji.eentertainment.ui.common.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pxkeji.universalproject.R


class ImageSelectDialog : BaseDialog() {

    companion object {


        fun newInstance(): ImageSelectDialog {
            val fragment = ImageSelectDialog()


            return fragment
        }

        interface OnClickListener {
            fun onTakePhotoClick()

            fun onChooseFromAlbumClick()

            fun onCancelClick()

        }
    }

    var mOnClickListener: OnClickListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_image_select, container, false)

        val txtTakePhoto = view.findViewById(R.id.txtTakePhoto) as TextView
        val txtChooseFromAlbum = view.findViewById(R.id.txtChooseFromAlbum) as TextView
        val txtCancel = view.findViewById(R.id.txtCancel) as TextView

        mOnClickListener?.let {onClickListener ->

            txtTakePhoto.setOnClickListener {
                onClickListener.onTakePhotoClick()
            }

            txtChooseFromAlbum.setOnClickListener {
                onClickListener.onChooseFromAlbumClick()
            }

            txtCancel.setOnClickListener {
                onClickListener.onCancelClick()
            }
        }



        return view
    }
}