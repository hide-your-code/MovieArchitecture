package minhdtm.example.shared.domain.pref

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import minhdtm.example.shared.data.pref.PreferenceRepository
import minhdtm.example.shared.di.IoDispatcher
import minhdtm.example.shared.domain.CoroutineUseCase

class OnboardingCompleteUseCase @Inject constructor(
    private val prefs: PreferenceRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, Boolean>(dispatcher) {

    override suspend fun execute(parameter: Unit): Boolean = prefs.onboardingCompleted
}
