package com.cainiao.study.net

import androidx.annotation.Keep

//用户学习情况
@Keep
data class StudyInfoRsp(
    val rank: Int,//学习总排行榜
    val today_study_time: Int,//今日学习时长
    val total_study_time: Int//总学习时长 分钟
)

//已经学习的课程数据
@Keep
data class StudiedRsp(
    val datas: List<Data>?,
    val page: Int,
    val size: Int,
    val total: Int,
    val total_page: Int
) {
    @Keep
    data class Data(
        val brief: String?,
        val comment_count: Int,
        val cost_price: Double,
        val course: Course?,
        val course_type: Int,
        val current_price: Double,
        val get_method: Int,
        val first_category: FirstCategory?,
        val id: Int,
        val left_expriy_days: Int,//剩余天数？
        var img_url: String?,//接口返回的，部分残缺了http://host,
        val is_distribution: Boolean,
        val is_free: Int,
        val is_live: Int,
        val is_pt: Boolean,
        val lessons_count: String?,
        val lessons_played_time: Int,
        val name: String?,
        val now_price: Double,
        val number: Int,
        val original_price: Double,
        val progress: Double,
        val title: String?
    ) {
        @Keep
        data class Course(
            val h5site: String?,
            val id: Int,
            val img_url: String?,
            val name: String?,
            val website: String?
        )

        @Keep
        data class FirstCategory(
            val code: String?,
            val id: Int,
            val title: String?
        )
    }
}

//已经购买的课程，班级 信息

@Keep
data class BoughtRsp(
    val datas: List<Data>?,
    val page: Int,
    val size: Int,
    val total: Int,
    val total_page: Int
) {
    @Keep
    data class Data(
        val cancel_time: String?,
        val course: Course?,
        val created_time: String?,
        val get_method: Int,
        val id: Int,
        val is_expired: Boolean,
        val left_expriy_days: Int,//剩余天数？
        val order_time: String?,
        val product_id: Int,
        val product_type: Int
    ) {
        @Keep
        data class Course(
            val brief: String?,
            val comment_count: Int,
            val cost_price: Double,
            val course: Course?,
            val first_category: FirstCategory?,
            val id: Int,
            val img_url: String?,
            val is_distribution: Boolean,
            val is_free: Int,
            val is_live: Int,
            val is_pt: Boolean,
            val lessons_played_time: Int,
            val name: String?,
            val now_price: Double,
            val number: Int,
            val progress: Double,
            val title: String?
        ) {
            @Keep
            data class Course(
                val h5site: String?,
                val id: Int,
                val img_url: String?,
                val name: String?,
                val website: String?
            )

            @Keep
            data class FirstCategory(
                val code: String?,
                val id: Int,
                val title: String?
            )
        }
    }
}
