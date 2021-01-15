package minhdtm.example.shared.domain.pref

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import minhdtm.example.shared.data.pref.PreferenceRepository
import minhdtm.example.shared.di.IoDispatcher
import minhdtm.example.shared.domain.CoroutineUseCase

class OnboardingCompleteActionUseCase @Inject constructor(
    private val prefs: PreferenceRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Boolean, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameter: Boolean) {
        prefs.onboardingCompleted = parameter
    }
}
