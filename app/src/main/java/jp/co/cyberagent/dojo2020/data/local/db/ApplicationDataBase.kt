package jp.co.cyberagent.dojo2020.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jp.co.cyberagent.dojo2020.data.local.db.converter.AccountListConverter
import jp.co.cyberagent.dojo2020.data.local.db.draft.DraftDao
import jp.co.cyberagent.dojo2020.data.local.db.draft.DraftEntity
import jp.co.cyberagent.dojo2020.data.local.db.memo.MemoDao
import jp.co.cyberagent.dojo2020.data.local.db.memo.MemoEntity
import jp.co.cyberagent.dojo2020.data.local.db.profile.ProfileDao
import jp.co.cyberagent.dojo2020.data.local.db.profile.ProfileEntity

@TypeConverters(AccountListConverter::class)
@Database(
    entities = [MemoEntity::class, DraftEntity::class, ProfileEntity::class],
    version = 1
)
abstract class ApplicationDataBase : RoomDatabase() {
    abstract fun memoDao(): MemoDao

    abstract fun draftDao(): DraftDao

    abstract fun profileDao(): ProfileDao
}