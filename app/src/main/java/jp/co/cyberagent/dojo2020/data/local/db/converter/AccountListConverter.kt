package jp.co.cyberagent.dojo2020.data.local.db.converter

import androidx.room.TypeConverter
import jp.co.cyberagent.dojo2020.data.model.Account

class AccountListConverter {
    @TypeConverter
    fun fromAccountList(list: List<Account>?): String? {
        return list?.joinToString(separator) { account -> accountToString(account) }
    }

    @TypeConverter
    fun accountListToString(string: String?): List<Account>? {
        val accountsString = string?.split(separator)
        return accountsString?.map { stringToAccount(it) }
    }

    companion object {
        const val separator = ","
        const val separatorForAccount = "-"
    }

    private fun accountToString(account: Account): String {
        return listOf(
            account.serviceName,
            account.id,
            account.url
        ).joinToString(separatorForAccount)
    }

    private fun stringToAccount(string: String): Account {
        val splitted = string.split(separatorForAccount)

        val serviceName = splitted[0]
        val id = splitted[1]
        val url = splitted[2]

        return Account(serviceName, id, url)
    }
}