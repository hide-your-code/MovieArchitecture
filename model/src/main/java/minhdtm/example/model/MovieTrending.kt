package minhdtm.example.model

import com.google.gson.annotations.SerializedName

data class MovieTrending(

    @SerializedName("page")
    val page: Int = 0,

    @SerializedName("results")
    val results: List<MovieTrendingItem> = emptyList(),

    @SerializedName("total_results")
    val totalResults: Int = 0,

    @SerializedName("total_pages")
    val totalPages: Int = 0
)

data class MovieTrendingItem(

    @SerializedName("poster_path")
    private val posterPath: String = "",

    @SerializedName("id")
    val id: Int,

    @SerializedName("vote_average")
    val voteAverage: Float = 0f,

    @SerializedName("genre_ids")
    val genreIds: List<Int> = emptyList(),

    @SerializedName("title")
    val title: String = ""
) {

    val poster: String
        get() = "$BASE_IMAGE_URL$posterPath"
}

enum class MovieTrendingType(val type: String) {
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    UPCOMING("upcoming")
}
