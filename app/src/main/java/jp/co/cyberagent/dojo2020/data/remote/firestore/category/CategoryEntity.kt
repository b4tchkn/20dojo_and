package jp.co.cyberagent.dojo2020.data.remote.firestore.category

import jp.co.cyberagent.dojo2020.data.model.Category

data class CategoryEntity(
    val name: String? = null
) {
    fun modelOrNull(): Category? {
        name ?: return null

        return Category(name)
    }
}