package minhdtm.example.model

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("genres")
    val genres: List<Genre>? = null
) {
    companion object {
        val empty = Genres(emptyList())
    }
}

data class Genre(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null
)
