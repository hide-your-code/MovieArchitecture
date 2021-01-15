package minhdtm.example.movieapparchitecture.ui.onboarding.welcome

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import minhdtm.example.movieapparchitecture.ui.onboarding.finsih.FinishOnboardingFragment
import minhdtm.example.movieapparchitecture.ui.onboarding.first.FirstOnboardingFragment
import minhdtm.example.movieapparchitecture.ui.onboarding.second.SecondOnboardingFragment

/**
 * Create adapter for ViewPager2
 */
class WelcomeOnboardingAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = arrayOf(FirstOnboardingFragment(), SecondOnboardingFragment(), FinishOnboardingFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
