package jp.co.cyberagent.dojo2020.data.local.db.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String
)