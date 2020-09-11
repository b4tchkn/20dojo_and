package jp.co.cyberagent.dojo2020.data.model

sealed class Text(
    val id: String,
    val title: String,
    val contents: String,
    val category: String
)

data class Left(val value: Draft) : Text(value.id, value.title, value.content, value.category)
data class Right(val value: Memo) : Text(value.id, value.title, value.contents, value.category)

fun Draft.toText(): Text = Left(this)
fun Memo.toText(): Text = Right(this)