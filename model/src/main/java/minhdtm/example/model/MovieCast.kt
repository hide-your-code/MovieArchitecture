package minhdtm.example.model

import com.google.gson.annotations.SerializedName

data class MovieCastAndCrew(

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("cast")
    val cast: List<Cast> = emptyList()
)

data class Cast(

    @SerializedName("adult")
    val adult: Boolean = false,

    @SerializedName("gender")
    val gender: Int = 0,

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("original_name")
    val originalName: String = "",

    @SerializedName("character")
    val character: String = "",

    @SerializedName("profile_path")
    val profilePath: String = ""
) {

    val profile: String
        get() = "$BASE_IMAGE_URL$profilePath"
}
