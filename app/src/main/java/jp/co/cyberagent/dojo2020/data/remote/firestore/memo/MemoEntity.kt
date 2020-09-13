package jp.co.cyberagent.dojo2020.data.remote.firestore.memo

import jp.co.cyberagent.dojo2020.data.model.Category
import jp.co.cyberagent.dojo2020.data.model.Memo

data class MemoEntity(
    val id: String? = null,
    val title: String? = null,
    val contents: String? = null,
    val time: Long? = null,
    val categoryName: String? = null
) {
    fun modelOrNull(): Memo? {
        val containsNull = listOf(id, title, contents, time, categoryName).contains(null)
        if (containsNull) {
            return null
        }

        val category = Category(categoryName!!)

        return Memo(id!!, title!!, contents!!, time!!, category)
    }

}