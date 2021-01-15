package minhdtm.example.shared.data.paging

//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import minhdtm.example.shared.data.local.LocalDatabase
//import minhdtm.example.shared.data.local.entities.MoviePopularEntity
//import minhdtm.example.shared.data.local.entities.RemoteKeyMoviePopularEntity
//import minhdtm.example.shared.data.remote.ApiClient
//import retrofit2.HttpException
//import java.io.IOException
//import java.io.InvalidObjectException
//import javax.inject.Inject

//@OptIn(ExperimentalPagingApi::class)
//class MoviePopularRemoteMediator @Inject constructor(
//    private val api: ApiClient,
//    private val local: LocalDatabase
//) : RemoteMediator<Int, MoviePopularEntity>() {
//
//    override suspend fun load(loadType: LoadType, state: PagingState<Int, MoviePopularEntity>): MediatorResult {
//        val page = when (loadType) {
//            LoadType.REFRESH -> {
//                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
//                remoteKeys?.nextKey?.minus(1) ?: 1
//            }
//            LoadType.PREPEND -> {
//                val remoteKey = getRemoteKeyForFirstItem(state)
//                    ?: throw InvalidObjectException("Remote key and the prevKey should not be null")
//                val prevKey = remoteKey.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
//                prevKey
//            }
//            LoadType.APPEND -> {
//                val remoteKey = getRemoteKeyForLastItem(state)
//                if (remoteKey?.nextKey == null) {
//                    throw InvalidObjectException("Remote key and the prevKey should not be null")
//                }
//                remoteKey.nextKey
//            }
//        }
//
//        try {
//            val apiResponse = api.getMoviePopular(page).results
//            val endOfPaginationReached = apiResponse?.isEmpty() ?: true
//
//            local.withTransaction {
//                // clear all table in the database
//                if (loadType == LoadType.REFRESH) {
//                    local.remoteKeyMoviePopular().clearAll()
//                    local.moviePopular().clearAll()
//                }
//
//                val prevKey = if (page == 1) null else page - 1
//                val nextKey = if (endOfPaginationReached) null else page + 1
//                val keys = apiResponse?.map {
//                    RemoteKeyMoviePopularEntity(id = it.id, prevKey = prevKey, nextKey = nextKey)
//                }
//
//                keys?.let {
//                    local.remoteKeyMoviePopular().insertAll(it)
//                }
//
//                apiResponse?.map { it.toEntity() }?.let {
//                    local.moviePopular().insertAll(it)
//                }
//            }
//
//            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
//        } catch (exception: IOException) {
//            return MediatorResult.Error(exception)
//        } catch (exception: HttpException) {
//            return MediatorResult.Error(exception)
//        }
//    }
//
//    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MoviePopularEntity>): RemoteKeyMoviePopularEntity? =
//        state.lastItemOrNull()?.let {
//            local.remoteKeyMoviePopular().keyMoviePopularId(it.id)
//        }
//
//    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MoviePopularEntity>): RemoteKeyMoviePopularEntity? =
//        state.firstItemOrNull()?.let {
//            local.remoteKeyMoviePopular().keyMoviePopularId(it.id)
//        }
//
//    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MoviePopularEntity>): RemoteKeyMoviePopularEntity? =
//        state.anchorPosition?.let { position ->
//            state.closestItemToPosition(position)?.id?.let {
//                local.remoteKeyMoviePopular().keyMoviePopularId(it)
//            }
//        }
//}
