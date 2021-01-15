package minhdtm.example.movieapparchitecture.ui.onboarding.first

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.databinding.FragmentFirstOnboardingBinding
import minhdtm.example.movieapparchitecture.ui.base.BaseFragment
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.FRAGMENT_FIRST_ONBOARDING

@AndroidEntryPoint
class FirstOnboardingFragment : BaseFragment<FragmentFirstOnboardingBinding, FirstOnboardingViewModel>() {

    override val screenName: String = FRAGMENT_FIRST_ONBOARDING

    override val viewModel: FirstOnboardingViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_first_onboarding
}
