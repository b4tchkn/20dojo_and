package jp.co.cyberagent.dojo2020.data.model

import java.util.*

data class Memo(
    val id: String,
    val title: String,
    val contents: String,
    val time: Long,
    val category: String
) {
    companion object {
        fun createWithOutId(title: String, contents: String, time: Long, category: String): Memo {
            val id = UUID.randomUUID().toString()

            return Memo(id, title, contents, time, category)
        }
    }
}