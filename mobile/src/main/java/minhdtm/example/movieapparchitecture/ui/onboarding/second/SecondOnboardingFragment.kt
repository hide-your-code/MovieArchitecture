package minhdtm.example.movieapparchitecture.ui.onboarding.second

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.databinding.FragmentSecondOnboardingBinding
import minhdtm.example.movieapparchitecture.ui.base.BaseFragment
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.FRAGMENT_SECOND_ONBOARDING

@AndroidEntryPoint
class SecondOnboardingFragment : BaseFragment<FragmentSecondOnboardingBinding, SecondOnboardingViewModel>() {

    override val screenName: String = FRAGMENT_SECOND_ONBOARDING

    override val viewModel: SecondOnboardingViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_second_onboarding
}
