package jp.co.cyberagent.dojo2020

import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import jp.co.cyberagent.dojo2020.data.*
import jp.co.cyberagent.dojo2020.data.local.*
import jp.co.cyberagent.dojo2020.data.local.db.ApplicationDataBase
import jp.co.cyberagent.dojo2020.data.remote.firestore.memo.DefaultFireStoreMemoDataSource
import jp.co.cyberagent.dojo2020.data.remote.firestore.memo.FireStoreMemoDataSource
import jp.co.cyberagent.dojo2020.data.remote.firestore.profile.DefaultFireStoreProfileDataSource
import jp.co.cyberagent.dojo2020.data.remote.firestore.profile.FireStoreProfileDataSource

object DI {

    fun injectDefaultMemoRepository(context: Context): MemoRepository {
        val localDataSource = injectDefaultMemoDataSource(context)
        val remoteDataSource = injectDefaultFireStoreMemoDataSource()

        return DefaultMemoRepository(localDataSource, remoteDataSource)
    }

    fun injectDefaultProfileRepository(context: Context): ProfileRepository {
        val profileDataSource = injectDefaultProfileDataSource(context)
        val fireStoreProfileDataSource = injectDefaultFireStoreProfileDataSource()

        return DefaultProfileRepository(profileDataSource, fireStoreProfileDataSource)
    }

    fun injectDefaultDraftRepository(context: Context): DraftRepository {
        val dataSource = injectDefaultDraftDataSource(context)

        return DefaultDraftRepository(dataSource)
    }

    fun injectDefaultUserInfoRepository(): UserInfoRepository {
        return DefaultUserInfoRepository()
    }

    private fun injectDefaultFireStoreProfileDataSource(): FireStoreProfileDataSource {
        val fireStore = injectFireStore()

        return DefaultFireStoreProfileDataSource(fireStore)
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
        val firestore = injectFireStore()

        return DefaultFireStoreMemoDataSource(firestore)
    }

    private fun injectFireStore(): FirebaseFirestore {
        return Firebase.firestore
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