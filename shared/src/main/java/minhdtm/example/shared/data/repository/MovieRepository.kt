package minhdtm.example.shared.data.repository

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import minhdtm.example.model.Cast
import minhdtm.example.model.Movie
import minhdtm.example.model.MovieRelease
import minhdtm.example.model.MovieTrendingItem
import minhdtm.example.shared.data.remote.ApiClient
import minhdtm.example.shared.result.Result
import javax.inject.Inject

interface MovieRepository {

    fun getMovie(movieId: Int): Flow<Result<Movie>>

    fun getMovieTrending(type: String): Flow<Result<List<MovieTrendingItem>>>

    fun getCast(movieId: Int): Flow<Result<List<Cast>>>

    fun getRelease(movieId: Int): Flow<Result<MovieRelease>>
}

class DefaultMovieRepository @Inject constructor(
    private val remote: ApiClient,
    gson: Gson
) : MovieRepository, BaseRepository(gson) {

    override fun getMovieTrending(type: String): Flow<Result<List<MovieTrendingItem>>> = flow {
        emitData(remote.getMovieTrending(type)) { it.results }
    }

    override fun getMovie(movieId: Int): Flow<Result<Movie>> = flow {
        emitData(remote.getMovie(movieId)) { it }
    }

    override fun getCast(movieId: Int): Flow<Result<List<Cast>>> = flow {
        emitData(remote.getMovieCastAndCrew(movieId)) { it.cast }
    }

    override fun getRelease(movieId: Int): Flow<Result<MovieRelease>> = flow {
        emitData(remote.getMovieRelease(movieId)) { it }
    }
}
