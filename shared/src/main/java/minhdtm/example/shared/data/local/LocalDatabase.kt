package minhdtm.example.shared.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import minhdtm.example.shared.data.local.converters.UtilConverters
import minhdtm.example.shared.data.local.dao.MoviePopularDao
import minhdtm.example.shared.data.local.dao.MoviePopularKeysDao
import minhdtm.example.shared.data.local.entities.MoviePopularEntity
import minhdtm.example.shared.data.local.entities.RemoteKeyMoviePopularEntity

@Database(entities = [MoviePopularEntity::class, RemoteKeyMoviePopularEntity::class], version = 1, exportSchema = false)
@TypeConverters(UtilConverters::class)
abstract class LocalDatabase : RoomDatabase() {

    // For movie popular
    abstract fun moviePopular(): MoviePopularDao
    abstract fun remoteKeyMoviePopular(): MoviePopularKeysDao
}
