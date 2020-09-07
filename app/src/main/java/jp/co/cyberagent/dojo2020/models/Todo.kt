package jp.co.cyberagent.dojo2020.models

data class Todo (
    val id: Int,
    val title: String,
    val hour: Int,
    val minute: Int,
    val description: String
)