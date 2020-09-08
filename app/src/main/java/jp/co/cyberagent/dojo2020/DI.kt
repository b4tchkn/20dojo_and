package jp.co.cyberagent.dojo2020

import android.content.Context
import androidx.room.Room
import jp.co.cyberagent.dojo2020.data.local.MemoDataSource
import jp.co.cyberagent.dojo2020.data.DefaultMemoRepository
import jp.co.cyberagent.dojo2020.data.MemoRepository
import jp.co.cyberagent.dojo2020.data.local.DefaultDraftDataSource
import jp.co.cyberagent.dojo2020.data.local.DefaultMemoDataSource
import jp.co.cyberagent.dojo2020.data.local.DraftDataSource
import jp.co.cyberagent.dojo2020.data.local.db.ApplicationDataBase
import jp.co.cyberagent.dojo2020.data.remote.firestore.DefaultFireStoreDataSource
import jp.co.cyberagent.dojo2020.data.remote.firestore.FireStoreDataSource

object DI {
    fun injectRepository(context: Context): MemoRepository {
        val localDataSource = injectLocalDataSource(context)
        val remoteDataSource = injectRemoteDataSource()

        return DefaultMemoRepository(localDataSource, remoteDataSource)
    }

    private fun injectLocalDataSource(context: Context): MemoDataSource {
        val database = injectDatabase(context)

        return DefaultMemoDataSource(database)
    }

    private fun injectDefaultDraftDataSource(context: Context): DraftDataSource {
        val dataBase = injectDatabase(context)

        return DefaultDraftDataSource(dataBase)
    }

    private fun injectRemoteDataSource(): FireStoreDataSource {
        return DefaultFireStoreDataSource()
    }

    private fun injectDatabase(context: Context): ApplicationDataBase {
        val database = Room.databaseBuilder(
            context,
            ApplicationDataBase::class.java,
            "ca-memo-database"
        ).build()

        return database
    }
}