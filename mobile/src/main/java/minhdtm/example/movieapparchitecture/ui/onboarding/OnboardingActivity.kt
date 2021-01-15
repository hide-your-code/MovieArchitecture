package minhdtm.example.movieapparchitecture.ui.onboarding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.updatePadding
import dagger.hilt.android.AndroidEntryPoint
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.databinding.ActivityOnboardingBinding
import minhdtm.example.movieapparchitecture.extension.doOnApplyWindowInserts
import minhdtm.example.movieapparchitecture.ui.base.BaseActivity
import minhdtm.example.movieapparchitecture.ui.onboarding.welcome.WelcomeFragment
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.ACTIVITY_ONBOARDING
import minhdtm.example.shared.extension.inTransaction

@AndroidEntryPoint
class OnboardingActivity : BaseActivity<ActivityOnboardingBinding, OnboardingViewModel>() {

    override val layoutId: Int = R.layout.activity_onboarding

    override val viewModel: OnboardingViewModel by viewModels()

    override val screenName: String = ACTIVITY_ONBOARDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup fragment
        setupFragment()

        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                add(R.id.fragment_container, WelcomeFragment())
            }
        }
    }

    private fun setupFragment() {
        // Immersive mode so images can draw behind the status bar
        binding.fragmentContainer.doOnApplyWindowInserts { view, windowInsetsCompat, viewPaddingState ->
            view.updatePadding(top = viewPaddingState.top + windowInsetsCompat.systemWindowInsetTop)
        }
    }
}
