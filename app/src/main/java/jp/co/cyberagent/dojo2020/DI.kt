package jp.co.cyberagent.dojo2020

import android.content.Context
import androidx.room.Room
import jp.co.cyberagent.dojo2020.data.DataSource
import jp.co.cyberagent.dojo2020.data.Repository
import jp.co.cyberagent.dojo2020.data.local.LocalDataSource
import jp.co.cyberagent.dojo2020.data.local.MemoDataBase

object DI {
    fun injectRepository(context: Context): DataSource {
        val localDataSource = injectLocalDataSource(context)
        val remoteDataSource = injectRemoteDataSource()

        return Repository(localDataSource, remoteDataSource)
    }

    fun injectLocalDataSource(context: Context): DataSource {
        val database = injectDatabase(context)

        return LocalDataSource(database)
    }

    fun injectRemoteDataSource(): DataSource {
        TODO()
    }

    private fun injectDatabase(context: Context): MemoDataBase {
        val database = Room.databaseBuilder(
            context,
            MemoDataBase::class.java,
            "ca-memo-database"
        ).build()

        return database
    }
}