package jp.co.cyberagent.dojo2020.data.local.db.converter

import androidx.room.TypeConverter
import jp.co.cyberagent.dojo2020.data.model.Account
import jp.co.cyberagent.dojo2020.data.model.Category

class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: Category): String? {
        return category.name
    }

    @TypeConverter
    fun stringToCategory(string: String): Category {
        return Category(string)
    }
}