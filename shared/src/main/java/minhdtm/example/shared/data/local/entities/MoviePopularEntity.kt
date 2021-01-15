package minhdtm.example.shared.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
//import minhdtm.example.model.MoviePopularItem

@Entity(tableName = "movie_popular")
data class MoviePopularEntity(
    @PrimaryKey val id: Int,
    val poster: String? = null,
    val voteAverage: Float? = null,
    val genreIds: List<Int>,
    val title: String? = null
)

//fun MoviePopularItem.toEntity() = MoviePopularEntity(
//    id = id,
//    poster = poster,
//    voteAverage = voteAverage,
//    genreIds = genreIds!!,
//    title = title
//)
