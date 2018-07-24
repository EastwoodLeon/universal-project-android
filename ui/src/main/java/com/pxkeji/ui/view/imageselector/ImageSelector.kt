package com.pxkeji.ui.view.imageselector

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView

class ImageSelector : RecyclerView {

    var mList = ArrayList<ImageBean>()

    lateinit var mAdapter: ImageSelectorAdapter

    constructor(context: Context) : super(context) {
        init()
    }



    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        val layoutManager = GridLayoutManager(context, 3)
        setLayoutManager(layoutManager)

        addOne()


        mAdapter = ImageSelectorAdapter(mList)

        mAdapter.mOnClickListener = object : ImageSelectorAdapter.OnClickListener {
            override fun onImageClick(bean: ImageBean) {




                // 最后一张，再添一张
                if (mList.indexOf(bean) + 1 == mList.size) {
                    addOne()
                    mAdapter.notifyDataSetChanged()
                }
            }

            override fun onDeleteClick(bean: ImageBean) {

                if (mList.size > 1) {
                    val index = mList.indexOf(bean)
                    mList.remove(bean)
                    mAdapter.notifyItemRemoved(index)

                }
            }

        }

        adapter = mAdapter
    }

    private fun addOne() {
        val bean = ImageBean()
        mList.add(bean)

    }
}