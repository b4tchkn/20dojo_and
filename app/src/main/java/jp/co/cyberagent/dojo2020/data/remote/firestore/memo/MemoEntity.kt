package jp.co.cyberagent.dojo2020.data.remote.firestore.memo

data class MemoEntity(
    val id: String? = null,
    val title: String? = null,
    val contents: String? = null,
    val time: Long? = null,
    val category: String? = null
)