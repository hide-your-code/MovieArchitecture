package minhdtm.example.movieapparchitecture.ui.launcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import minhdtm.example.movieapparchitecture.ui.base.BaseViewModel
import minhdtm.example.movieapparchitecture.util.LaunchDestination
import minhdtm.example.shared.domain.pref.OnboardingCompleteUseCase
import minhdtm.example.shared.result.Event
import minhdtm.example.shared.result.data
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(onboardingCompleteUseCase: OnboardingCompleteUseCase) : BaseViewModel() {

    val onboarding: LiveData<Event<LaunchDestination>> = liveData {
        val result = onboardingCompleteUseCase(Unit)
        if (result.data) {
            emit(Event(LaunchDestination.MAIN_ACTIVITY))
        } else {
            emit(Event(LaunchDestination.ONBOARDING))
        }
    }
}
