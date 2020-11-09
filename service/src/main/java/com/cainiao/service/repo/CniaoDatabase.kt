package com.cainiao.service.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

// 3. database

@Database(entities = [CniaoUserInfo::class], version = 1, exportSchema = false)
abstract class CniaoDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        private const val CNIAO_DB_NAME = "cniao_db"

        @Volatile
        private var instance: CniaoDatabase? = null

        fun getInstance(context: Context): CniaoDatabase {

            return instance ?: Room.databaseBuilder(
                context,
                CniaoDatabase::class.java,
                CNIAO_DB_NAME
            ).build().also { instance = it }


        }
    }

}

// room 数据库 三步使用：

// 1.entity 定义
@Entity(tableName = "tb_cniao_user")
data class CniaoUserInfo(
    @PrimaryKey
    val id: Int, // 主键
    val course_permission: Boolean,
    val token: String?,
    @Embedded
    val user: User? // 嵌套 类,支持挂载的形式
) {
    data class User(
        @ColumnInfo(name = "cniao_user_id")
        val id: Int, // 用户 ID
        val logo_url: String?, // 用户头像
        val reg_time: String?, // 注册时间
        val username: String? // 用户名
    )

}

// 2. dao 层定义

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // 策略
    fun insertUser(user: CniaoUserInfo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: CniaoUserInfo)

    @Delete
    fun deleteUser(user: CniaoUserInfo)

    // ==========

    @Query("select * from tb_cniao_user where id = :id")
    fun queryLiveUser(id: Int = 0): LiveData<CniaoUserInfo>

    @Query("select * from tb_cniao_user where id = :id")
    fun queryUser(id: Int = 0): CniaoUserInfo?

}