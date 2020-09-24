package com.cainiao.common.db

import androidx.room.*

/**
 * @author boomhe on 2020/9/23.
 */
@Entity(
    tableName = "db_user", foreignKeys = [ForeignKey(
        entity = Book::class,
        parentColumns = ["bid"],
        childColumns = ["bookId"],
        onDelete = ForeignKey.SET_DEFAULT
    )], indices = [Index("uid"), Index("bookId")]
)
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

    @Embedded // 嵌套类 不需要子类是 @Entity
    var baby: Child? = null

    var bookId: Int = 0


}

data class Child(
    val cid: Int,
    val cname: String,
    val cAge: Int,
    val sex: Int
)

@Entity
data class Book(
    @PrimaryKey
    val bid: Int,
    val name: String,
    val price: Double
)

// 表明该数据类是sql的执行结果数据，可用于其他的dao操作,用于class较为合适
@DatabaseView(
    "select uname,name from db_user,book where uid = 3 or bookId =3",
    viewName = "tempBean")
class TempBean {
    var uname = ""
    var name = ""
    override fun toString(): String {
        return "TempBean(uname='$uname', name='$name')"
    }
}