package minhdtm.example.shared.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import minhdtm.example.shared.data.local.entities.MoviePopularEntity

@Dao
interface MoviePopularDao : BaseDao<MoviePopularEntity> {

    @Query("SELECT * FROM movie_popular ORDER BY id DESC")
    fun movie(): PagingSource<Int, MoviePopularEntity>

    @Query("DELETE FROM movie_popular")
    suspend fun clearAll()
}
