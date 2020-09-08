package jp.co.cyberagent.dojo2020.test

import android.content.ContentValues.TAG
import android.util.Log
import jp.co.cyberagent.dojo2020.data.ProfileRepository
import jp.co.cyberagent.dojo2020.data.model.Account
import jp.co.cyberagent.dojo2020.data.model.Profile
import kotlinx.coroutines.flow.flow

object TestProfileRepository : ProfileRepository {

    override suspend fun saveProfile(uid: String?, profile: Profile) {
        Log.d(TAG, profile.toString())
    }

    override suspend fun fetchProfile(uid: String?) = flow {
        val twitter = Account("twitter", "twitterID", "twitter.com")
        val github = Account("github", "githubID", "github.com")
        val accountList = listOf(twitter, github)

        val profile = Profile("USERNAME", "iconURL", accountList)
        emit(profile)
    }
}