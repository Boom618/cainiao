package com.cainiao.service.repo

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// DataBase 操作类
object CniaoDbHelper {

    // return liveData形式
    fun getLiveUserInfo(context: Context) =
        CniaoDatabase.getInstance(context).userDao.queryLiveUser()

    //  以普通数据对象的形式，获取userInfo
    fun getUserInfo(context: Context) = CniaoDatabase.getInstance(context).userDao.queryUser()

    // 删除数据表中的用户数据
    fun deleteUserInfo(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            getUserInfo(context)?.let {
                CniaoDatabase.getInstance(context).userDao.deleteUser(it)
            }
        }
    }

    // 新增
    fun insertUserInfo(context: Context, user: CniaoUserInfo) {
        GlobalScope.launch(Dispatchers.IO) {
            CniaoDatabase.getInstance(context).userDao.insertUser(user)
        }
    }
}