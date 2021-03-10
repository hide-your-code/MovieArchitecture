package minhdtm.example.shared.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import minhdtm.example.shared.util.GsonHelper

class UtilConverters {

    @TypeConverter
    fun fromList(list: List<Int>): String = GsonHelper.create().toJson(list)

    @TypeConverter
    fun toList(result: String): List<Int> = Gson().fromJson(result, object : TypeToken<List<Int>>() {}.type)
}
