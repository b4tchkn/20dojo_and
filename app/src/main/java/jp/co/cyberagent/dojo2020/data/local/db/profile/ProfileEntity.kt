package jp.co.cyberagent.dojo2020.data.local.db.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jp.co.cyberagent.dojo2020.data.model.Account

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "icon")
    val iconUrl: String,

    @ColumnInfo(name = "account_list")
    val accountList: List<Account>
)