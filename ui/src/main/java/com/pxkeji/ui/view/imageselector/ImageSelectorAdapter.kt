package com.pxkeji.ui.view.imageselector

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.pxkeji.ui.R

class ImageSelectorAdapter(
        var list: ArrayList<ImageBean>
) : RecyclerView.Adapter<ImageSelectorAdapter.Companion.ViewHolder>() {

    private var mContext: Context? = null

    var mOnClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mContext == null) {
            mContext = parent.context
        }

        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.image_selector, parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = list[position]

        holder.image.setOnClickListener {
            mOnClickListener?.onImageClick(bean)
        }

        holder.delete.setOnClickListener {
            mOnClickListener?.onDeleteClick(bean)
        }

    }

    companion object {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val image: ImageView = itemView.findViewById(R.id.image)
            val delete: ImageView = itemView.findViewById(R.id.delete)
        }
    }

    interface OnClickListener {
        fun onImageClick(bean: ImageBean)
        fun onDeleteClick(bean: ImageBean)
    }
}