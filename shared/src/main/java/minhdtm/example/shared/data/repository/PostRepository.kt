package minhdtm.example.shared.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import minhdtm.example.model.Genres
import minhdtm.example.shared.data.remote.ApiClient
import minhdtm.example.shared.result.Result
import javax.inject.Inject
import javax.inject.Singleton

interface PostRepository {
    fun getPost(): Flow<Result<Genres>>
}

@Singleton
open class DefaultPostRepository @Inject constructor(private val apiClient: ApiClient) : PostRepository {

    override fun getPost(): Flow<Result<Genres>> = flow {
        emit(Result.Loading)
        if (apiClient.getPost().isSuccessful) {
            emit(Result.Success(apiClient.getPost().body() ?: Genres.empty))
        } else {
            emit(Result.Error(Exception("Error")))
        }
    }
}
