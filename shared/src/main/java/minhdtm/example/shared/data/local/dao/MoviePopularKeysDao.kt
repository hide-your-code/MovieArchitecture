package minhdtm.example.shared.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import minhdtm.example.shared.data.local.entities.RemoteKeyMoviePopularEntity

@Dao
interface MoviePopularKeysDao : BaseDao<RemoteKeyMoviePopularEntity> {

    @Query("SELECT * FROM movie_popular_key WHERE id = :movieId")
    suspend fun keyMoviePopularId(movieId: Int): RemoteKeyMoviePopularEntity

    @Query("DELETE FROM movie_popular_key")
    suspend fun clearAll()
}
