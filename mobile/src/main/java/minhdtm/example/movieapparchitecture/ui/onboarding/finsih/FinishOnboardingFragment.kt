package minhdtm.example.movieapparchitecture.ui.onboarding.finsih

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.databinding.FragmentFinishOnboardingBinding
import minhdtm.example.movieapparchitecture.ui.base.BaseFragment
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.FRAGMENT_FINISH_ONBOARDING

@AndroidEntryPoint
class FinishOnboardingFragment : BaseFragment<FragmentFinishOnboardingBinding, FinishOnboardingViewModel>() {

    override val screenName: String = FRAGMENT_FINISH_ONBOARDING

    override val viewModel: FinishOnboardingViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_finish_onboarding
}
