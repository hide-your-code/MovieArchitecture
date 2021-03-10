package minhdtm.example.shared.domain.movietrending

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import minhdtm.example.model.MovieTrendingItem
import minhdtm.example.model.MovieTrendingType
import minhdtm.example.shared.data.repository.MovieTrendingRepository
import minhdtm.example.shared.di.MainDispatcher
import minhdtm.example.shared.domain.FlowUseCase
import minhdtm.example.shared.result.Result
import javax.inject.Inject

class GetMovieTrendingUseCase @Inject constructor(
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
    private val moviePopular: MovieTrendingRepository
) : FlowUseCase<Int, List<MovieTrendingItem>>(dispatcher) {

    override fun execute(parameters: Int): Flow<Result<List<MovieTrendingItem>>> =
        moviePopular.getMovieTrending(
            when (parameters) {
                1 -> {
                    MovieTrendingType.TOP_RATED
                }
                2 -> {
                    MovieTrendingType.UPCOMING
                }
                else -> {
                    MovieTrendingType.POPULAR
                }
            }.type
        )
}
