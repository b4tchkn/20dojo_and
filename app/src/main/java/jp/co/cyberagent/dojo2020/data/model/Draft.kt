package jp.co.cyberagent.dojo2020.data.model

data class Draft(
    val id: String,
    val title: String,
    val content: String,
    val startTime: Long,
    val category: Category
)