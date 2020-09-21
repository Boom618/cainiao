package com.cainiao.jetpack

import android.app.Application
import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.blankj.utilcode.util.Utils
import com.cainiao.jetpack.db.ItemBean
import com.cainiao.jetpack.db.UserDataBase

/**
 * @author boomhe on 2020/9/18.
 */
class MyApplication : Application() {

    private val worker = OneTimeWorkRequestBuilder<ListWorker>().build()

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)

        WorkManager.getInstance(this).enqueue(worker)
    }

    // workManage

    class ListWorker(private val context: Context, parameters: WorkerParameters) :
        Worker(context, parameters) {
        override fun doWork(): Result {

            val itemDao = UserDataBase.getInstance(context).getItemDao()
            val list = mutableListOf<ItemBean>()
            for (i in 0..300) {
                list.add(ItemBean(i, "新增数据 $i ", i % 9))
            }
            itemDao.insert(list)
            return Result.success()
        }
    }
}