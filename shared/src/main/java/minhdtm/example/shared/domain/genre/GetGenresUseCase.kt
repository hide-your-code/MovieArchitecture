package minhdtm.example.shared.domain.genre

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import minhdtm.example.model.Genres
import minhdtm.example.shared.data.repository.GenresRepository
import minhdtm.example.shared.di.MainDispatcher
import minhdtm.example.shared.domain.FlowUseCase
import minhdtm.example.shared.result.Result
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    @MainDispatcher dispatcher: CoroutineDispatcher,
    private val genres: GenresRepository
) : FlowUseCase<Unit, Genres>(dispatcher) {

    override fun execute(parameters: Unit): Flow<Result<Genres>> = genres.getGenres()
}
