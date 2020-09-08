package jp.co.cyberagent.dojo2020.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.co.cyberagent.dojo2020.data.local.db.draft.DraftDao
import jp.co.cyberagent.dojo2020.data.local.db.draft.DraftEntity
import jp.co.cyberagent.dojo2020.data.local.db.memo.MemoDao
import jp.co.cyberagent.dojo2020.data.local.db.memo.MemoEntity

@Database(entities = [MemoEntity::class, DraftEntity::class], version = 1)
abstract class ApplicationDataBase : RoomDatabase() {
    abstract fun memoDao(): MemoDao

    abstract fun draftDao(): DraftDao
}