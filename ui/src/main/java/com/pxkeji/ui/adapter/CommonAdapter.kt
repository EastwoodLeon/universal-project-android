package com.pxkeji.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class CommonAdapter<T>(
        val list: ArrayList<T>
) : RecyclerView.Adapter<CommonAdapter.Companion.ViewHolder>() {

    private var context: Context? = null

    abstract var layoutId: Int

    companion object {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (context == null) context = parent.context

        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)

        val holder = ViewHolder(view)

        return holder
    }

    override fun getItemCount() = list.size


}