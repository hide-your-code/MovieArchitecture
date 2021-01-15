package minhdtm.example.shared.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import minhdtm.example.shared.result.Result
import timber.log.Timber

abstract class CoroutineUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    @SuppressWarnings("TooGenericExceptionCaught")
    suspend operator fun invoke(parameter: P): Result<R> = try {
        withContext(coroutineDispatcher) {
            execute(parameter).let {
                Result.Success(it)
            }
        }
    } catch (e: Exception) {
        Timber.d(e)
        Result.Error(e)
    }

    protected abstract suspend fun execute(parameter: P): R
}
