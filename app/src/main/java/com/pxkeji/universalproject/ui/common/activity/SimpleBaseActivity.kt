package com.pxkeji.universalproject.ui.common.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.pxkeji.universalproject.R


/**
 * 最基本的活动，只有返回操作
 */
abstract class SimpleBaseActivity : BaseActivity() {

    protected var mTvToolbarTitle: TextView? = null

    /**
     * 指明布局
     */
    abstract val mLayoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayoutResId)

        initData()


        val toolbar: Toolbar? = findViewById(R.id.toolbar)
        toolbar?.let {
            setSupportActionBar(it)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            it.setNavigationOnClickListener { finish() }
        }


        mTvToolbarTitle = findViewById(R.id.tv_toolbar_title)
        initViews()
    }



    /**
     * 数据初始化，比如从Intent、SharedPreference中读取数据
     */
    abstract fun initData()

    /**
     * View初始化，为View填充数据
     */
    abstract fun initViews()
}