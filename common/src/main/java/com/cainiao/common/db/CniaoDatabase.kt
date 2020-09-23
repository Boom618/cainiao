package com.cainiao.common.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author boomhe on 2020/9/23.
 * 抽象类，[ 在运行过程中，会根据该抽象类，有字类去实现]
 */
@Database(entities = [DbUser::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao


    companion object {
        const val DB_NAME = "user.db"
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java, DB_NAME
                )
                    // 默认room不允许在主线程操作数据库，这里设置允许
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}