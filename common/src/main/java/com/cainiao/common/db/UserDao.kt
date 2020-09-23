package com.cainiao.common.db

import androidx.room.*

/**
 * @author boomhe on 2020/9/23.
 *
 * Dao  层的创建可以是 接口 也可以是 抽象类
 */
@Dao
interface UserDao {

    @Query("select * from db_user")
    fun queryAll(): List<DbUser>

    @Query("select * from db_user where uid = :id")
    fun queryById(id: Int): DbUser?

    @Query("select * from db_user where uid in (:userIds)")
    fun queryByIds(userIds: IntArray): List<DbUser>

    @Query("select * from db_user where uname like :name and age = :age limit 1")
    fun queryByName(name: String?, age: Int): DbUser?

    @Insert
    fun insertAll(vararg userDao: DbUser)

    @Delete
    fun delete(user: DbUser)

    // 更新，冲突策略
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: DbUser?)
}