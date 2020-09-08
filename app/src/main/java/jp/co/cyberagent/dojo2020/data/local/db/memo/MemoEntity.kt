package jp.co.cyberagent.dojo2020.data.local.db.memo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "memos")
data class MemoEntity(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "contents")
    val contents: String,

    @ColumnInfo(name = "time")
    val time: Double
) {
    companion object {
        fun createForInsert(title: String, contents: String, time: Double): MemoEntity {
            return MemoEntity(UUID.randomUUID().toString(), title, contents, time)
        }
    }
}