package com.cainiao.jetpack.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.cainiao.jetpack.adapter.MyAdapter
import com.cainiao.jetpack.db.ItemBean
import com.cainiao.jetpack.db.UserDataBase

/**
 * @author boomhe on 2020/9/18.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val diffCallBack = object : DiffUtil.ItemCallback<ItemBean>() {
        override fun areItemsTheSame(oldItem: ItemBean, newItem: ItemBean): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: ItemBean, newItem: ItemBean): Boolean {

            return oldItem.name == newItem.name
        }
    }

    val mAdapter = MyAdapter(diffCallBack)
    val liveList: LiveData<PagedList<ItemBean>>

    // 每一个类都有 init 方法，调用完 构造方法后就执行 init 方法
    init {

        val itemDao = UserDataBase.getInstance(getApplication()).getItemDao()

        liveList = LivePagedListBuilder(itemDao.queryAll(),
            PagedList.Config.Builder()
                    // 初始大小
                .setInitialLoadSizeHint(100)
                    // 一页加载数
                .setPageSize(50)
                    // 预加载数据
                .setPrefetchDistance(10)
                .setEnablePlaceholders(false)
                .build())
            .build()
    }

}