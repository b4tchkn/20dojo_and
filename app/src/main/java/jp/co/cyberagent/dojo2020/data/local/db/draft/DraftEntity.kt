package jp.co.cyberagent.dojo2020.data.local.db.draft

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jp.co.cyberagent.dojo2020.data.model.Category
import jp.co.cyberagent.dojo2020.data.model.Draft

@Entity(tableName = "drafts")
data class DraftEntity(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "contents")
    val contents: String,

    @ColumnInfo(name = "start_time")
    val startTime: Long,

    @ColumnInfo(name = "category")
    val category: Category
) {

    fun toModel(): Draft {
        return Draft(id, title, contents, startTime, category)
    }
}