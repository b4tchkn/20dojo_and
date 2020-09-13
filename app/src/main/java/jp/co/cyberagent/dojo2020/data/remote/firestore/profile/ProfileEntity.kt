package jp.co.cyberagent.dojo2020.data.remote.firestore.profile

import jp.co.cyberagent.dojo2020.data.model.Account
import jp.co.cyberagent.dojo2020.data.model.Profile

data class AccountEntity(
    val serviceName: String? = null,
    val id: String? = null,
    val url: String? = null
) {

    fun toModelOrNull(): Account? {
        val list = listOf(serviceName, id, url)
        if (list.contains(null)) {
            return null
        }

        return Account(serviceName!!, id!!, url!!)
    }
}

data class ProfileEntity(
    val name: String? = null,
    val iconUrl: String? = null,
    val accountEntityList: List<AccountEntity>? = null
) {

    fun toModel(): Profile {
        return Profile(
            name,
            iconUrl,
            accountEntityList?.mapNotNull { it.toModelOrNull() })
    }
}