package minhdtm.example.model

import com.google.gson.annotations.SerializedName

data class Genres(

    @SerializedName("genres")
    val genres: List<Genre> = emptyList()
)

data class Genre(

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String = ""
)
