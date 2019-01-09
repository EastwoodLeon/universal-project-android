package com.pxkeji.ui.view.imageselector

import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.view.ViewGroup
import com.pxkeji.ui.R
import com.pxkeji.ui.view.imageselector.ImageBean.Companion.ImageType


class ImageSelector : RecyclerView {

    companion object {
        private const val TAG = "ImageSelector"
    }

    val mList = ArrayList<ImageBean>()

    lateinit var mAdapter: ImageSelectorAdapter

    var mOnImageClickListener: OnImageClickListener? = null

    var mMaxCount = 9

    var images: List<String>
        get() = mList.map { it.path }
        set(value) {

            object : AsyncTask<List<String>, Unit, Unit>() {
                override fun doInBackground(vararg p0: List<String>?) {
                    p0[0]?.let {
                        mList.clear()
                        mList += it.map {
                            ImageBean().apply {
                                imageType = ImageType.REMOTE
                                path = it
                            }
                        }

                        if (mList.size < mMaxCount) {
                            addOne()
                        }
                    }
                }

                override fun onPostExecute(result: Unit?) {
                    mAdapter.notifyDataSetChanged()
                }
            }.execute(value)




        }

    constructor(context: Context) : super(context) {
        init(null)
    }



    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {

        val ta = context.obtainStyledAttributes(attrs, R.styleable.ImageSelector)
        val addIcon = ta.getDrawable(R.styleable.ImageSelector_is_add_icon)
        val imageMargin = ta.getInteger(R.styleable.ImageSelector_is_image_margin, 0)
        val columnCount = ta.getInteger(R.styleable.ImageSelector_is_column_count, 3)
        mMaxCount = ta.getInteger(R.styleable.ImageSelector_is_max_count, 9)
        ta.recycle()

        val layoutManager = GridLayoutManager(context, columnCount)
        setLayoutManager(layoutManager)

        addOne()



        mAdapter = ImageSelectorAdapter(mList, context.resources.displayMetrics.widthPixels, addIcon, imageMargin, columnCount)

        mAdapter.mOnClickListener = object : ImageSelectorAdapter.OnClickListener {
            override fun onImageClick(bean: ImageBean) {

                mOnImageClickListener?.onImageClick(bean)



            }

            override fun onDeleteClick(bean: ImageBean) {

                if (mList.size > 1) {



                    val index = mList.indexOf(bean)
                    mList.remove(bean)
                    mAdapter.notifyItemRemoved(index)

                    // 删除一张后，如果最后一张有图片，那么就需要再加一个
                    if (mList.last().path != "") {
                        addOne()
                        mAdapter.notifyItemInserted(mList.size - 1)
                    }
                }
            }

        }

        adapter = mAdapter
    }



    private fun addOne() {
        val bean = ImageBean()
        mList.add(bean)

    }

    fun loadItemImage(bean: ImageBean) {

        val position = mList.indexOf(bean)

        mAdapter.notifyItemChanged(position)


        // 最后一张，且没有达到上限，就再添一张
        if (position + 1 == mList.size && mList.size < mMaxCount) {
            addOne()
            mAdapter.notifyItemInserted(mList.size - 1)
        }

    }




    interface OnImageClickListener {
        fun onImageClick(bean: ImageBean)
    }
}