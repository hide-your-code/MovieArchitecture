package minhdtm.example.shared.data.remote

import minhdtm.example.model.Genres
import minhdtm.example.model.MovieTrending
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET("genre/movie/list")
    suspend fun getGenres(): Response<Genres>

    @GET("movie/{trending}")
    suspend fun getMovieTrending(@Path("trending") trending: String): Response<MovieTrending>
}
