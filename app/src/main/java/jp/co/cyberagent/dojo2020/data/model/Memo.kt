package jp.co.cyberagent.dojo2020.data.model

import java.util.*

data class Memo(val id: String, val title: String, val contents: String, val time: Double) {
    companion object {
        fun createWithOutId(title: String, contents: String, time: Double): Memo {
            val id = UUID.randomUUID().toString()

            return Memo(id, title, contents, time)
        }
    }
}