package minhdtm.example.model

import com.google.gson.annotations.SerializedName

data class MovieRelease(

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("results")
    val result: List<MovieReleaseResult> = emptyList()
)

data class MovieReleaseResult(

    @SerializedName("iso_3166_1")
    val iso3166: String = "",

    @SerializedName("release_dates")
    val releaseDates: List<MovieReleaseDate> = emptyList()
)

data class MovieReleaseDate(

    @SerializedName("certification")
    val certification: String = "",

    @SerializedName("iso_639_1")
    val iso639: String = "",

    @SerializedName("release_date")
    val releaseDate: String = "",

    @SerializedName("type")
    val type: String = "",

    @SerializedName("note")
    val note: String = ""
)
