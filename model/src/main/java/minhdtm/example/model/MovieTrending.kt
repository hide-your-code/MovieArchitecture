package minhdtm.example.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieTrending(
    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("results")
    val results: List<MovieTrendingItem>? = null,

    @SerializedName("total_results")
    val totalResults: Int? = null,

    @SerializedName("total_pages")
    val totalPages: Int? = null
) : Serializable {
    companion object {
        val empty = MovieTrending(
            0,
            emptyList(),
            0,
            0
        )
    }
}

data class MovieTrendingItem(
    @SerializedName("poster_path")
    private val posterPath: String? = null,

    @SerializedName("id")
    val id: Int,

    @SerializedName("vote_average")
    val voteAverage: Float? = null,

    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,

    @SerializedName("title")
    val title: String? = null
) {
    val poster: String
        get() = "$BASE_IMAGE_URL$posterPath"
}

enum class MovieTrendingType(val type: String) {
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    UPCOMING("upcoming")
}
