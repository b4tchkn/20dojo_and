package jp.co.cyberagent.dojo2020.data.remote.firestore.profile

data class AccountEntity(val serviceName: String? = null, val id: String? = null, val url: String? = null)

data class ProfileEntity(
    val name: String? = null,
    val iconUrl: String? = null,
    val accountEntityList: List<AccountEntity>? = null
)