package jp.co.cyberagent.dojo2020.data.remote.firestore

data class MemoEntity(
    val id: String? = null,
    val title: String? = null,
    val contents: String? = null,
    val time: Double? = null,
    val category: String? = null
)