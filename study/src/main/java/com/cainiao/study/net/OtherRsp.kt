package com.cainiao.study.net

import androidx.annotation.Keep

/**
 * 页面模块的配置列表
 */
class ComponentListRsp : ArrayList<ComponentItem>()

@Keep
data class ComponentItem(
    val apply_deadline_time: String,
    val balance_payment_time: Any?,
    val button_desc: Any?,
    val course: Course,
    val created_time: String,
    val current_price: Double,
    val graduate_time: String,
    val id: Int,
    val is_apply_stop: Int,
    val learning_mode: Int,
    val lessons_count: Any?,
    val number: Int,
    val original_price: Double?,
    val start_class_time: String,
    val status: Int,
    val stop_use_down_payment_time: Any?,
    val student_count: Int,
    val student_limit: Int,
    val study_expiry_day: Int,
    val teacher_ids: String,
    val title: String
) {
    @Keep
    data class Course(
        val h5site: String,
        val id: Int,
        val img_url: String,
        val name: String,
        val website: String
    )
}

@Keep
data class PageListRsp(
    val datas: List<Data>?,
    val page: Int,
    val size: Int,
    val total: Int,
    val total_page: Int
) {
    @Keep
    data class Data(
        val name: String?,
        val page_id: Int,
        val platform: String?
    )
}

class PageModuleListRsp : ArrayList<PageModuleItem>()

@Keep
data class PageModuleItem(
    val created_time: String?,
    val data_url: String?,
    val id: Int,
    val is_show_more: Int,
    val layout: Int,
    val more_redirect_url: Any?,
    val scroll: Int,
    val sub_title: Any?,
    val title: String?,
    val type: Int
)
//endregion


//region 课程相关

@Keep
data class HasCoursePermission(
    val cancel_time: String,
    val days: Int,
    val get_method: Int,
    val is_true: Int, //1 有 0 无
    val type: String,
)

//课程播放相关，通过key查询返回
@Keep
data class PlayCourseRsp(
    val is_live: Int,
    val last_play_info: LastPlayInfo?,
    val play_urls: PlayUrls?,
    val video_info_id: Int
) {
    @Keep
    data class LastPlayInfo(
        val key: String?,
        val position: Int,
        val title: String?
    )

    @Keep
    data class PlayUrls(
        val flv: Flv?,
        val hls: Hls?,
        val mp4: Mp4?,
        val origin: String?,
        val rtmp: Rtmp?
    ) {
        @Keep
        data class Flv(
            val is_encryption: Int,
            val urls: Urls?
        ) {
            @Keep
            data class Urls(
                val hd: String?,
                val sd: String?,
                val shd: String?
            )
        }

        @Keep
        data class Hls(
            val is_encryption: Int,
            val urls: Urls?
        ) {
            @Keep
            data class Urls(
                val hd: String?,
                val sd: String?,
                val shd: String?
            )
        }

        @Keep
        data class Mp4(
            val is_encryption: Int,
            val urls: Urls?
        ) {
            @Keep
            data class Urls(
                val hd: String?,
                val sd: String?,
                val shd: String?
            )
        }

        @Keep
        data class Rtmp(
            val is_encryption: Int,
            val urls: Urls?
        ) {
            @Keep
            data class Urls(
                val hd: String?,
                val sd: String?,
                val shd: String?
            )
        }
    }
}


//fixme 使用post请求，在拦截器里面做签名运算的时候，这些item都要当作string才行，不要用int，或其他数组类型
@Keep
data class FavoriteReq(
    val course_id: String,//课程id
    val type: String,//1 收藏 2 取消收藏
    val user_id: String = "0"//默认当前用户
)

@Keep
data class FavoriteRsp(
    val message: String?,
    val result: Int
)

class RecommendListRsp : ArrayList<RecommendItem>()

@Keep
data class RecommendItem(
    val brief: String?,
    val comment_count: Int,
    val cost_price: Double,
    val first_category: FirstCategory?,
    val id: Int,
    val img_url: String?,
    val is_distribution: Boolean,
    val is_free: Int,
    val is_live: Int,
    val is_pt: Boolean,
    val lessons_count: Int,
    val lessons_played_time: Int,
    val name: String?,
    val now_price: Double
) {
    @Keep
    data class FirstCategory(
        val code: String?,
        val id: Int,
        val title: String?
    )
}

//课程的课时章节
class ChapterListRsp : ArrayList<ChapterItem>()

@Keep
data class ChapterItem(
    val bsort: Int,
    val class_id: Int,
    val id: Int,
    val lessons: List<Lesson>?,
    val title: String?
) {
    @Keep
    data class Lesson(
        val bsort: Int,
        val is_free: Int,
        val is_live: Int,
        val key: String?,
        val lesson_id: Int,
        val live_begin_time: String?,
        val live_end_time: String?,
        val live_plan_begin_time: String?,
        val live_plan_end_time: String?,
        val live_status: Int,
        val name: String?,
        val state: Int,
        val video_duration: String?,
        val video_info_duration: Int
    )
}

@Keep
data class CourseDetailInfo(
    val brief: String?,
    val can_buy: Int,
    val can_use_coupon: Int,
    val class_difficulty: Int,
    val comment_count: Int,
    val cost_price: Double,
    val course_type: Int,
    val created_time: String?,
    val desc: String?,//html格式的
    val expiry_day: Int,
    val first_category: FirstCategory?,
    val fit_to: String?,
    val goal: String?,
    val h5site: String?,
    val id: Int,
    val img_url: String?,
    val is_distribution: Boolean,
    val is_free: Int,
    val is_live: Int,
    val is_pt: Boolean,
    val lessons_count: Int,
    val lessons_finished_count: Int,
    val lessons_played_time: Int,
    val lessons_time: Int,
    val name: String?,
    val now_price: Double,
    val pre_tech: String?,
    val qr_img_url: String?,
    val recommend_count: Int,
    val sub_title: String?,
    val teacher: Teacher?,
    val teacher_ids: String?,
    val website: String?
) {
    @Keep
    data class FirstCategory(
        val code: String?,
        val id: Int,
        val title: String?
    )

    @Keep
    data class Teacher(
        val brief: String?,
        val company: String?,
        val id: Int,
        val job_title: String?,
        val logo_url: String?,
        val teacher_name: String?
    )
}

//endregion

//banner
class BannerListRsp : ArrayList<BannerItem>()

@Keep
data class BannerItem(
    val client_url: String?,
    val created_time: String?,
    val id: Int,
    val img_url: String?,
    val name: Any?,
    val order_num: Int,
    val page_show: Int,
    val redirect_url: String?,
    val state: Int,
    val type: String?
)

//region 讲师相关

@Keep
data class TeacherListRsp(
    val datas: List<Teacher>?,
    val page: Int,
    val size: Int,
    val total: Int,
    val total_page: Int
) {
    @Keep
    data class Teacher(
        val brief: String?,
        val company: String?,
        val id: Int,
        val job_title: String?,
        val logo_url: String?,
        val teacher_name: String?
    )
}

//讲师的课程
class TeacherCourseListRsp : ArrayList<TeacherCourseItem>()

@Keep
data class TeacherCourseItem(
    val brief: String?,
    val comment_count: Int,
    val cost_price: Double,
    val first_category: FirstCategory?,
    val id: Int,
    val img_url: String?,
    val is_distribution: Boolean,
    val is_pt: Boolean,
    val lessons_played_time: Int,
    val name: String?,
    val now_price: Double
) {
    @Keep
    data class FirstCategory(
        val code: String?,
        val id: Int,
        val title: String?
    )
}


@Keep
data class TeacherInfoRsp(
    val brief: String?,
    val company: String?,
    val courses: Int,
    val id: Int,
    val is_follow: Int,
    val job_title: String?,
    val logo_url: String?,
    val students: Int,
    val teacher_name: String?
)
//endregion