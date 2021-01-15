package minhdtm.example.movieapparchitecture.ui.launcher

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import minhdtm.example.movieapparchitecture.extension.checkAllMatched
import minhdtm.example.movieapparchitecture.extension.eventObserve
import minhdtm.example.movieapparchitecture.ui.MainActivity
import minhdtm.example.movieapparchitecture.ui.onboarding.OnboardingActivity
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.ACTIVITY_LAUNCH
import minhdtm.example.movieapparchitecture.util.LaunchDestination
import minhdtm.example.movieapparchitecture.util.LaunchDestination.MAIN_ACTIVITY
import minhdtm.example.movieapparchitecture.util.LaunchDestination.ONBOARDING
import minhdtm.example.shared.analytics.AnalyticsHelper

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {

    val viewModel: LauncherViewModel by viewModels()

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Gửi tracking
        analyticsHelper.sendScreenView(ACTIVITY_LAUNCH, this, null)

        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.onboarding.eventObserve(this, ::launchOnboardingOrMain)
    }

    private fun launchOnboardingOrMain(launch: LaunchDestination) {
        when (launch) {
            MAIN_ACTIVITY -> startActivity(Intent(this, MainActivity::class.java))
            ONBOARDING -> startActivity(Intent(this, OnboardingActivity::class.java))
        }.checkAllMatched
        finish()
    }
}
