package minhdtm.example.shared.domain.movie

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import minhdtm.example.model.MovieReleaseDate
import minhdtm.example.shared.data.repository.MovieRepository
import minhdtm.example.shared.di.MainDispatcher
import minhdtm.example.shared.domain.FlowUseCase
import minhdtm.example.shared.result.Result
import javax.inject.Inject

//class GetMovieReleaseUseCase @Inject constructor(
//    @MainDispatcher coroutineDispatcher: CoroutineDispatcher,
//    private val movie: MovieRepository
//) : FlowUseCase<Int, MovieReleaseDate>(coroutineDispatcher) {
//
//    override fun execute(parameters: Int): Flow<Result<MovieReleaseDate>> = movie.getRelease(parameters).map {
//
//    }
//}
