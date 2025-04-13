package ru.fillandroid.socialnetwork.data.data_source.database.type_converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.fillandroid.socialnetwork.data.data_source.data.ImageField

class ImageFieldTypeConverter {

    @TypeConverter
    fun stringToImageField(string: String?): ImageField? {
        return Gson().fromJson(string, ImageField::class.java)
    }

    @TypeConverter
    fun imageFieldToString(imageField: ImageField?): String? {
        return Gson().toJson(imageField)
    }
}
