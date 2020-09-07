package jp.co.cyberagent.dojo2020.data.local.db

import android.app.Application
import android.util.Log
import androidx.room.Room

class Application : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // AppDatabaseをビルドする
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
        Log.i("test:  ", "build Database" )
    }
}