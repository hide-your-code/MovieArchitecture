package minhdtm.example.shared.domain.movie

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import minhdtm.example.model.Cast
import minhdtm.example.shared.data.repository.MovieRepository
import minhdtm.example.shared.di.MainDispatcher
import minhdtm.example.shared.domain.FlowUseCase
import minhdtm.example.shared.result.Result
import javax.inject.Inject

class GetMovieCastUseCase @Inject constructor(
    @MainDispatcher dispatcher: CoroutineDispatcher,
    private val movie: MovieRepository
) : FlowUseCase<Int, List<Cast>>(dispatcher) {

    override fun execute(parameters: Int): Flow<Result<List<Cast>>> = movie.getCast(parameters)
}
