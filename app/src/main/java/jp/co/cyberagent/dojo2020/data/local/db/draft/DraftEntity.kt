package jp.co.cyberagent.dojo2020.data.local.db.draft

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drafts")
data class DraftEntity(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "contents")
    val contents: String,

    @ColumnInfo(name = "start_time")
    val startTime: Double,

    @ColumnInfo(name = "category")
    val category: String
)