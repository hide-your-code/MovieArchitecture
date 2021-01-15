package minhdtm.example.movieapparchitecture.ui.onboarding.welcome

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import minhdtm.example.shared.domain.pref.OnboardingCompleteActionUseCase
import minhdtm.example.shared.result.Event

class WelcomeViewModel @ViewModelInject constructor(
    private val onboardingCompleteActionUseCase: OnboardingCompleteActionUseCase
) : ViewModel() {

    private val _navigateMainActivity = MutableLiveData<Event<Unit>>()
    val navigateMainActivity: LiveData<Event<Unit>> = _navigateMainActivity

    fun getStartedClick() {
        viewModelScope.launch {
            onboardingCompleteActionUseCase(true)
            _navigateMainActivity.postValue(Event(Unit))
        }
    }
}
