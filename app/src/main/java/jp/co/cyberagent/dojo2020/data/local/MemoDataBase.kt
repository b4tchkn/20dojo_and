package jp.co.cyberagent.dojo2020.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MemoEntity::class], version = 1)
abstract class MemoDataBase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}