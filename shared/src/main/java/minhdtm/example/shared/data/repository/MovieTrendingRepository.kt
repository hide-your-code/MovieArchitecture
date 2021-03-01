package minhdtm.example.shared.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import minhdtm.example.model.MovieTrendingItem
import minhdtm.example.shared.data.remote.ApiClient
import minhdtm.example.shared.result.Result
import javax.inject.Inject

interface MovieTrendingRepository {

    fun getMovieTrending(type: String): Flow<Result<List<MovieTrendingItem>>>
}

class DefaultMovieTrendingRepository @Inject constructor(private val remote: ApiClient) : MovieTrendingRepository {

    override fun getMovieTrending(type: String): Flow<Result<List<MovieTrendingItem>>> = flow {
        emit(Result.Loading)
        val dataRemote = remote.getMovieTrending(type)
        if (dataRemote.isSuccessful) {
            emit(Result.Success(dataRemote.body()?.results ?: emptyList()))
        } else {
            emit(Result.Error(Exception(dataRemote.errorBody()?.string())))
        }
    }
}
