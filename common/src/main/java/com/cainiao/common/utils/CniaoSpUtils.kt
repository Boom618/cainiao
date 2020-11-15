package com.cainiao.common.utils

import com.blankj.utilcode.util.PathUtils
import com.tencent.mmkv.MMKV

/**
 * 自定义的 K-V 轻量数据存储管理类
 */
object CniaoSpUtils {

    private val kv: MMKV by lazy {
        MMKV.defaultMMKV()
    }

    init {
        // /data/data/package/files
        MMKV.initialize(PathUtils.getInternalAppFilesPath())
    }

    fun put(key: String, value: Any?) {
        when (value) {
            is Boolean -> kv.putBoolean(key, value)
            is ByteArray -> kv.putBytes(key, value)
            is String -> kv.putString(key, value)
            is Int -> kv.putInt(key, value)
            is Long -> kv.putLong(key, value)
            is Float -> kv.putFloat(key, value)
            else -> error("${value?.javaClass?.simpleName} Not Supported !")
        }
    }

    fun getString(key: String, defValue: String? = null) = kv.getString(key, defValue)

    fun getBoolean(key: String, defValue: Boolean = false) = kv.getBoolean(key, defValue)

    fun getBytes(key: String, defValue: ByteArray? = null) = kv.getBytes(key, defValue)

    fun getInt(key: String, defValue: Int = 0) = kv.getInt(key, defValue)

    fun getLong(key: String, defValue: Long = 0L) = kv.getLong(key, defValue)

    fun getFloat(key: String, defValue: Float = 0f) = kv.getFloat(key, defValue)
}