package minhdtm.example.movieapparchitecture.ui.onboarding.welcome

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.databinding.FragmentWelcomeBinding
import minhdtm.example.movieapparchitecture.extension.autoClear
import minhdtm.example.movieapparchitecture.extension.eventObserve
import minhdtm.example.movieapparchitecture.ui.MainActivity
import minhdtm.example.movieapparchitecture.ui.base.BaseFragment
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.FRAGMENT_WELCOME
import minhdtm.example.movieapparchitecture.widget.ViewPager2Helper

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding, WelcomeViewModel>() {

    override val screenName: String = FRAGMENT_WELCOME

    override val viewModel: WelcomeViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_welcome

    private val handler = Handler(Looper.getMainLooper())

    private var adapter: WelcomeOnboardingAdapter by autoClear()
    private lateinit var pagerPager: ViewPager2Helper

    private val advancePager: Runnable = object : Runnable {
        override fun run() {
            pagerPager.advance()
            handler.postDelayed(this, AUTO_ADVANCE_DELAY)
        }
    }

    override fun initView(view: View) {
        super.initView(view)

        setupViewPager()
    }

    override fun bindViewModel() {
        viewModel.navigateMainActivity.eventObserve(viewLifecycleOwner) {
            requireActivity().run {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun setupViewPager() {
        pagerPager = ViewPager2Helper(binding.pager)
        adapter = WelcomeOnboardingAdapter(this)
        binding.pager.adapter = adapter
        binding.indicator.setViewPager2(binding.pager)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        handler.postDelayed(advancePager, INITIAL_ADVANCE_DELAY)
    }

    override fun onDestroyView() {
        // If token is null, all callbacks and messages will be remove
        handler.removeCallbacksAndMessages(null)
        super.onDestroyView()
    }

    companion object {
        private const val INITIAL_ADVANCE_DELAY = 3_000L
        private const val AUTO_ADVANCE_DELAY = 6_000L
    }
}
