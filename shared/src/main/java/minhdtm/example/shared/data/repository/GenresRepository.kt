package minhdtm.example.shared.data.repository

import com.google.gson.Gson
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
open class DefaultGenresRepository @Inject constructor(
    private val remote: ApiClient,
    gson: Gson
) : GenresRepository, BaseRepository(gson) {

    override fun getGenres(): Flow<Result<Genres>> = flow {
        emitData(remote.getGenres()) { it }
    }
}
