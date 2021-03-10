package minhdtm.example.shared.domain.post

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import minhdtm.example.model.Genres
import minhdtm.example.shared.data.repository.PostRepository
import minhdtm.example.shared.di.MainDispatcher
import minhdtm.example.shared.domain.FlowUseCase
import minhdtm.example.shared.result.Result
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    @MainDispatcher val mainDispatcher: CoroutineDispatcher,
    private val postRepository: PostRepository
) : FlowUseCase<Unit, Genres>(mainDispatcher) {

    override fun execute(parameters: Unit): Flow<Result<Genres>> = postRepository.getPost()
}
