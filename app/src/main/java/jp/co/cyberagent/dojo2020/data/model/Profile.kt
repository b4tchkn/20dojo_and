package jp.co.cyberagent.dojo2020.data.model

data class Account(val serviceName: String, val id: String, val url: String)
data class Profile(val name: String, val iconUrl: String, val accountList: List<Account>)