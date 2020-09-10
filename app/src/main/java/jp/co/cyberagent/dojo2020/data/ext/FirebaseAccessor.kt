package jp.co.cyberagent.dojo2020.data.ext

import jp.co.cyberagent.dojo2020.data.model.FirebaseUserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

suspend fun <T> Flow<FirebaseUserInfo?>.accessWithUid(
    consumer: suspend (uid: String?) -> T
): Unit = collect {

    consumer(it?.uid)
}