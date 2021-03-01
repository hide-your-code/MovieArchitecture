package minhdtm.example.shared.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_popular_key")
data class RemoteKeyMoviePopularEntity(
    @PrimaryKey override val id: Int,
    override val nextKey: Int?,
    override val prevKey: Int?
) : BaseRemoteKey()
