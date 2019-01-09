package com.pxkeji.ui.view.imageselector

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pxkeji.ui.R
import com.pxkeji.util.LogUtil

class ImageSelectorAdapter(
        private val list: ArrayList<ImageBean>,
        private val screenWidth: Int,
        private val addIcon: Drawable?,
        private val imageMargin: Int,
        private val columnCount: Int
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

        val lp = holder.image.layoutParams
        lp.height = screenWidth / columnCount - imageMargin
        holder.image.layoutParams = lp



        if (mContext != null) {

            if (bean.path != "") {
                Glide.with(mContext!!).load(bean.path).into(holder.image)
            } else if (addIcon != null) {
                holder.image.setImageDrawable(addIcon)
            } else {
                holder.image.setImageResource(R.drawable.add_image)
            }

        }

        holder.delete.visibility = if (bean == list.last() && bean.path == "") View.GONE else View.VISIBLE


        holder.image.setOnClickListener {
            mOnClickListener?.onImageClick(bean)
        }

        holder.delete.setOnClickListener {
            mOnClickListener?.onDeleteClick(bean)
        }






    }

    companion object {
        private const val TAG = "ImageSelectorA"
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