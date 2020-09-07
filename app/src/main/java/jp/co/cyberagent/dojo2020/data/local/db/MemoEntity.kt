package jp.co.cyberagent.dojo2020.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//DBのテーブル
@Entity(tableName = "memo_list")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "hour") val hour: Int,
    @ColumnInfo(name = "minute") val minute: Int,
    @ColumnInfo(name = "description") val description: String
) {
    companion object {
        fun createForInsert(title: String, hour: Int, minute: Int, description: String): MemoEntity {
            return MemoEntity(
                0,
                title,
                hour,
                minute,
                description
            )
        }
    }
}