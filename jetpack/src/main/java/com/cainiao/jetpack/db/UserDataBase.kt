package com.cainiao.jetpack.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author boomhe on 2020/9/18.
 * Dao 数据库操作层
 */
@Database(entities = [UserBean::class, State::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getStateDao(): StateDao

    companion object {

        private var instance: UserDataBase? = null

        fun getInstance(context: Context): UserDataBase {
            return instance ?: Room.databaseBuilder(
                context, UserDataBase::class.java, "user_db"
            )
                .build()
        }
    }

}