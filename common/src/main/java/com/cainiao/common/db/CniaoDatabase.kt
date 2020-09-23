package com.cainiao.common.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * @author boomhe on 2020/9/23.
 * 抽象类，[ 在运行过程中，会根据该抽象类，有字类去实现]
 */
@Database(
    entities = [DbUser::class, Book::class], version = 1,
    exportSchema = false, views = [TempBean::class]
)
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
                    .addMigrations(migration1_2)
                    .build()
            }
            return instance
        }

        // 数据库升级  迁移
        val migration1_2 = object : Migration(2, 1) {
            override fun migrate(database: SupportSQLiteDatabase) {
               //  database.execSQL("")

            }

        }
    }


}