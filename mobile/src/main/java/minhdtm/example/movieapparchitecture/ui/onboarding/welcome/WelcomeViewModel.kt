package minhdtm.example.movieapparchitecture.ui.onboarding.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import minhdtm.example.movieapparchitecture.ui.base.BaseViewModel
import minhdtm.example.shared.domain.pref.OnboardingCompleteActionUseCase
import minhdtm.example.shared.result.Event
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val onboardingCompleteActionUseCase: OnboardingCompleteActionUseCase
) : BaseViewModel() {

    private val _navigateMainActivity = MutableLiveData<Event<Unit>>()
    val navigateMainActivity: LiveData<Event<Unit>> = _navigateMainActivity

    fun getStartedClick() {
        viewModelScope.launch {
            onboardingCompleteActionUseCase(true)
            _navigateMainActivity.value = Event(Unit)
        }
    }
}
