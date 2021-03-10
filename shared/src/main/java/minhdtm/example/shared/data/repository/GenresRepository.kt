package minhdtm.example.shared.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import minhdtm.example.model.Genres
import minhdtm.example.shared.data.remote.ApiClient
import minhdtm.example.shared.result.Result
import javax.inject.Inject
import javax.inject.Singleton

interface GenresRepository {
    fun getGenres(): Flow<Result<Genres>>
}

@Singleton
open class DefaultGenresRepository @Inject constructor(private val remote: ApiClient) : GenresRepository {

    override fun getGenres(): Flow<Result<Genres>> = flow {
        emit(Result.Loading)
        if (remote.getGenres().isSuccessful) {
            emit(Result.Success(remote.getGenres().body() ?: Genres.empty))
        } else {
            emit(Result.Error(Exception("Error")))
        }
    }
}
