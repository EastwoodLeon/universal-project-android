package com.pxkeji.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class  CommonAdapter <T> (val list: List<T>)
    : RecyclerView.Adapter<CommonAdapter.Companion.ViewHolder>() {

    abstract val layoutId: Int
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (context == null) {
            context = parent.context
        }

        return ViewHolder(LayoutInflater.from(context)
                .inflate(layoutId, parent, false))
    }

    override fun getItemCount(): Int = list.size



    companion object {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}