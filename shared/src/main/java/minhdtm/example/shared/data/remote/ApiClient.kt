package minhdtm.example.shared.data.remote

import minhdtm.example.model.Genres
import minhdtm.example.model.Movie
import minhdtm.example.model.MovieCastAndCrew
import minhdtm.example.model.MovieRelease
import minhdtm.example.model.MovieTrending
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET("genre/movie/list")
    suspend fun getGenres(): Response<Genres>

    @GET("movie/{trending}")
    suspend fun getMovieTrending(@Path("trending") trending: String): Response<MovieTrending>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int): Response<Movie>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCastAndCrew(@Path("movie_id") movieId: Int): Response<MovieCastAndCrew>

    @GET("movie/{movie_id}/release_dates")
    suspend fun getMovieRelease(@Path("movie_id") movieId: Int): Response<MovieRelease>
}
