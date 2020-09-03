package jp.co.cyberagent.dojo2020.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memos")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "contents")
    val contents: String,

    @ColumnInfo(name = "time")
    val time: Double
) {
    companion object {
        fun createForInsert(title: String, contents: String, time: Double): MemoEntity {
            return MemoEntity(0, title, contents, time)
        }
    }
}