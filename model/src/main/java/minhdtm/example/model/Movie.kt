package minhdtm.example.model

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("adult")
    val adult: Boolean = false,

    @SerializedName("genres")
    val genres: List<Genre> = emptyList(),

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("imdb_id")
    val imdbId: String = "",

    @SerializedName("poster_path")
    val posterPath: String = "",

    @SerializedName("vote_count")
    val voteCount: Int = 0,

    @SerializedName("vote_average")
    val voteAverage: Float = 0f,

    @SerializedName("original_title")
    val originTitle: String = "",

    @SerializedName("overview")
    val overview: String = "",

    @SerializedName("status")
    val status: String = "",

    @SerializedName("runtime")
    val runtime: Int = 0,

    @SerializedName("release_date")
    val releaseDate: String = ""
) {

    val poster: String
        get() = "$BASE_IMAGE_URL$posterPath"
}
