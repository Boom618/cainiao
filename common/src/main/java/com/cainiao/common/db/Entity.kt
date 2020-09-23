package com.cainiao.common.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * @author boomhe on 2020/9/23.
 */
@Entity(tableName = "db_user")
class DbUser {
    @PrimaryKey(autoGenerate = true)
    var uid = 0

    // 表示映射的字段名是 uname
    @ColumnInfo(name = "uname")
    var name: String? = null

    var city: String? = null

    var age = 0

    // 表中忽略该字段
    @Ignore
    var isSingle = false

}