package minhdtm.example.movieapparchitecture.ui.launcher

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import minhdtm.example.movieapparchitecture.util.LaunchDestination
import minhdtm.example.shared.domain.pref.OnboardingCompleteUseCase
import minhdtm.example.shared.result.Event
import minhdtm.example.shared.result.data

class LauncherViewModel @ViewModelInject constructor(onboardingCompleteUseCase: OnboardingCompleteUseCase) :
    ViewModel() {

    val onboarding: LiveData<Event<LaunchDestination>> = liveData {
        val result = onboardingCompleteUseCase(Unit)
        if (result.data == false) {
            emit(Event(LaunchDestination.ONBOARDING))
        } else {
            emit(Event(LaunchDestination.MAIN_ACTIVITY))
        }
    }
}
