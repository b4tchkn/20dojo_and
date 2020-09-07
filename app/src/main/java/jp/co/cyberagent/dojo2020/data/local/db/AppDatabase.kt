package jp.co.cyberagent.dojo2020.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

// 直接SQLliteと直接やり取りするポイント
@Database(entities=[MemoEntity::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}
