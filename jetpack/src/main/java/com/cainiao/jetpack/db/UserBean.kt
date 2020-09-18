package com.cainiao.jetpack.db

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * @author boomhe on 2020/9/18.
 *
 * Room 数据库
 */
@Entity(tableName = "tb_user")
data class UserBean(
    @PrimaryKey
    val uid: Int = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val sex: Int = 0,
    @ColumnInfo
    val age: Int,
    @ColumnInfo
    val city: String
)

@Dao
interface UserDao {

    @Query("select * from tb_user where uid = 0")
    fun query(): LiveData<UserBean>

    @Update
    fun upDate(userBean: UserBean)

    @Insert(entity = UserBean::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(userBean: UserBean)

    @Delete
    fun delete(userBean: UserBean)

}

@Entity(tableName = "tb_state")
data class State(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    @ColumnInfo
    val login: Boolean
)

@Dao
interface StateDao{
    @Query("select login from tb_state where id = 0")
    fun query():LiveData<Boolean>

    @Insert
    fun insert(state:State)
}
