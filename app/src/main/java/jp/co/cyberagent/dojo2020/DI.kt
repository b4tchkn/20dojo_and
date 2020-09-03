package jp.co.cyberagent.dojo2020

import android.content.Context
import androidx.room.Room
import jp.co.cyberagent.dojo2020.data.DataSource
import jp.co.cyberagent.dojo2020.data.Repository
import jp.co.cyberagent.dojo2020.data.local.LocalDataSource
import jp.co.cyberagent.dojo2020.data.local.MemoDataBase
import jp.co.cyberagent.dojo2020.data.remote.FireStoreDataSource
import jp.co.cyberagent.dojo2020.data.remote.RemoteDataSource

object DI {
    fun injectRepository(context: Context): DataSource {
        val localDataSource = injectLocalDataSource(context)
        val remoteDataSource = injectRemoteDataSource()

        return Repository(localDataSource, remoteDataSource)
    }

    private fun injectLocalDataSource(context: Context): DataSource {
        val database = injectDatabase(context)

        return LocalDataSource(database)
    }

    private fun injectRemoteDataSource(): FireStoreDataSource {
        return RemoteDataSource()
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