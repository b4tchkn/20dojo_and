package jp.co.cyberagent.dojo2020.data.model

import java.util.*

data class Category(val name: String)

data class Memo(
    val id: String,
    val title: String,
    val contents: String,
    val time: Long,
    val category: Category
) {
    companion object {
        fun createWithOutId(title: String, contents: String, time: Long, category: Category): Memo {
            val id = UUID.randomUUID().toString()

            return Memo(id, title, contents, time, category)
        }
    }
}