package com.cainiao.jetpack.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author boomhe on 2020/9/18.
 */
@Database(entities = [UserBean::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

}