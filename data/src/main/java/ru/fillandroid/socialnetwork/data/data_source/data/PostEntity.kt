package ru.fillandroid.socialnetwork.data.data_source.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.fillandroid.socialnetwork.data.data_source.database.type_converters.ImageFieldTypeConverter
import java.util.UUID

@Entity
data class PostEntity(
    @PrimaryKey val id: String,
    val description: String,
    @ColumnInfo(name = "first_image")
    @TypeConverters(ImageFieldTypeConverter::class)
    val firstImage: ImageField,
    @ColumnInfo(name = "second_image")
    @TypeConverters(ImageFieldTypeConverter::class)
    val secondImage: ImageField,
    val comment: String?
) {

    // Компаньён нужен просто для инициализации данных
    companion object {
        val posts = listOf(
            PostEntity(
                id = UUID.randomUUID().toString(),
                description = "Душа моя озарена неземной радостью, как эти чудесные весенние утра, которыми я наслаждаюсь от всего сердца. Я совсем один и блаженствую в зд",
                firstImage = ImageField(
                    imageId = UUID.randomUUID().toString(),
                    url = "https://i.pinimg.com/736x/c0/36/bd/c036bd3df03fc9fb7d33247f60d0f089.jpg",
                    isLiked = true
                ),
                secondImage = ImageField(
                    imageId = UUID.randomUUID().toString(),
                    url = "https://avatars.mds.yandex.net/i?id=0eaa142d7202ac9bbd26ac279e7ae159_l-4898876-images-thumbs&n=27&h=480&w=480",
                    isLiked = false
                ),
                comment = "Вот такой вот комментарий"
            ),
            PostEntity(
                id = UUID.randomUUID().toString(),
                description = "Проснувшись однажды утром после беспокойного сна, Грегор Замза обнаружил, что он у себя в постели превратился в страшное насекомое. Лежа под",
                firstImage = ImageField(
                    imageId = UUID.randomUUID().toString(),
                    url = "https://avatars.mds.yandex.net/i?id=0a8dc1da8b47ac63e1b06dadc55e1a3f_l-5141109-images-thumbs&n=27&h=480&w=480",
                    isLiked = false
                ),
                secondImage = ImageField(
                    imageId = UUID.randomUUID().toString(),
                    url = "https://img.goodfon.ru/wallpaper/big/d/1c/smailiki-zheltye-shary-ulybki.webp",
                    isLiked = false
                ),
                comment = ""
            ),
            PostEntity(
                id = UUID.randomUUID().toString(),
                description = "Далеко-далеко за словесными горами в стране гласных и согласных живут рыбные тексты. Вдали от всех живут они в буквенных домах на берегу Сем",
                firstImage = ImageField(
                    imageId = UUID.randomUUID().toString(),
                    url = "https://cdn.fishki.net/upload/post/2021/02/16/3613245/tn/alberta-2297204-1280.jpg",
                    isLiked = false
                ),
                secondImage = ImageField(
                    imageId = UUID.randomUUID().toString(),
                    url = "https://s00.yaplakal.com/pics/pics_original/9/9/2/19853299.jpg",
                    isLiked = false
                ),
                comment = ""
            ),
            PostEntity(
                id = UUID.randomUUID().toString(),
                description = "Любя, съешь щипцы, — вздохнёт мэр, — кайф жгуч. Шеф взъярён тчк щипцы с эхом гудбай Жюль. Эй, жлоб! Где туз? Прячь юных съёмщиц в шкаф. Экс-",
                firstImage = ImageField(
                    imageId = UUID.randomUUID().toString(),
                    url = "https://i.pinimg.com/564x/7f/11/98/7f1198cf14f7296efb6caf87592ab542.jpg",
                    isLiked = false
                ),
                secondImage = ImageField(
                    imageId = UUID.randomUUID().toString(),
                    url = "https://cdn.trinixy.ru/pics6/20220926/231781_1_trinixy_ru.jpg",
                    isLiked = false
                ),
                comment = ""
            ),
        )
    }
}
