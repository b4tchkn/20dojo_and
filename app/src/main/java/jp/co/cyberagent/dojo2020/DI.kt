package jp.co.cyberagent.dojo2020

import android.content.Context
import androidx.room.Room
import jp.co.cyberagent.dojo2020.data.*
import jp.co.cyberagent.dojo2020.data.local.*
import jp.co.cyberagent.dojo2020.data.local.db.ApplicationDataBase
import jp.co.cyberagent.dojo2020.data.remote.firestore.memo.DefaultFireStoreMemoDataSource
import jp.co.cyberagent.dojo2020.data.remote.firestore.memo.FireStoreMemoDataSource

object DI {
    fun injectDefaultMemoRepository(context: Context): MemoRepository {
        val localDataSource = injectDefaultMemoDataSource(context)
        val remoteDataSource = injectDefaultFireStoreMemoDataSource()

        return DefaultMemoRepository(localDataSource, remoteDataSource)
    }

    fun injectDefaultDraftRepository(context: Context): DraftRepository {
        val dataSource = injectDefaultDraftDataSource(context)

        return DefaultDraftRepository(dataSource)
    }

    fun injectDefaultUserInfoRepository(): UserInfoRepository {
        return DefaultUserInfoRepository()
    }

    private fun injectDefaultProfileDataSource(context: Context): ProfileDataSource {
        val database = injectDatabase(context)

        return DefaultProfileDataSource(database)
    }

    private fun injectDefaultMemoDataSource(context: Context): MemoDataSource {
        val database = injectDatabase(context)

        return DefaultMemoDataSource(database)
    }

    private fun injectDefaultDraftDataSource(context: Context): DraftDataSource {
        val dataBase = injectDatabase(context)

        return DefaultDraftDataSource(dataBase)
    }

    private fun injectDefaultFireStoreMemoDataSource(): FireStoreMemoDataSource {
        return DefaultFireStoreMemoDataSource()
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