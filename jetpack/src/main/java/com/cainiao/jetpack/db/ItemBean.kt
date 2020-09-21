package com.cainiao.jetpack.db

import androidx.paging.DataSource
import androidx.room.*

/**
 * @author boomhe on 2020/9/21.
 */
@Entity(tableName = "tb_item")
data class ItemBean(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val color: Int
)

@Dao
interface ItemDao {
    @Insert
    fun insert(item: List<ItemBean>)

    // 返回在 paging 组件中
    @Query("select * from tb_item")
    fun queryAll(): DataSource.Factory<Int, ItemBean>

}