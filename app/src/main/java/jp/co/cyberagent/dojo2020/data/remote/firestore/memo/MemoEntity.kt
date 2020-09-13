package jp.co.cyberagent.dojo2020.data.remote.firestore.memo

import jp.co.cyberagent.dojo2020.data.model.Memo

data class MemoEntity(
    val id: String? = null,
    val title: String? = null,
    val contents: String? = null,
    val time: Long? = null,
    val category: String? = null
) {
    fun modelOrNull(): Memo? {
        val containsNull = listOf(id, title, contents, time, category).contains(null)
        if (containsNull) {
            return null
        }

        return Memo(id!!, title!!, contents!!, time!!, category!!)
    }
}