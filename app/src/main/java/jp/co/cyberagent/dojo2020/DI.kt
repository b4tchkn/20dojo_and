package jp.co.cyberagent.dojo2020

import android.content.Context
import android.util.Log
import androidx.room.Room
import jp.co.cyberagent.dojo2020.data.MemoDataSource
import jp.co.cyberagent.dojo2020.data.DefaultRepository
import jp.co.cyberagent.dojo2020.data.Repository
import jp.co.cyberagent.dojo2020.data.local.LocalDataSource
import jp.co.cyberagent.dojo2020.data.local.db.AppDatabase

object DI {
    fun injectRepository(context: Context): Repository {
        Log.i("test: in DI", "injected Repository")
        val localDataSource = injectLocalDataSource(context)

        return DefaultRepository(localDataSource)
    }

    private fun injectLocalDataSource(context: Context): MemoDataSource {
        val database = injectDatabase(context)

        return LocalDataSource(database)
    }

    private fun injectDatabase(context: Context): AppDatabase {
        val database = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "memo_database"
        ).build()

        return database
    }
}