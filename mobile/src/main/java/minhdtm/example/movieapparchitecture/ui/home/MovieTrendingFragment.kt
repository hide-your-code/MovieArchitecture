package minhdtm.example.movieapparchitecture.ui.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.databinding.FragmentMovieTrendingBinding
import minhdtm.example.movieapparchitecture.extension.autoClear
import minhdtm.example.movieapparchitecture.ui.base.BaseFragment
import minhdtm.example.movieapparchitecture.util.Constant

@AndroidEntryPoint
class MovieTrendingFragment : BaseFragment<FragmentMovieTrendingBinding, MovieTrendingViewModel>() {

    override val viewModel: MovieTrendingViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_movie_trending

    override val screenName: String = Constant.ScreenName.FRAGMENT_HOME

    private var pagerAdapter: MovieTrendingPagerAdapter by autoClear()
    private var tabLayoutMediator: TabLayoutMediator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPager()
        setupTabLayout()
    }

    override fun onDestroyView() {
        tabLayoutMediator?.detach()
        tabLayoutMediator = null
        super.onDestroyView()
    }

    private fun setupPager() {
        pagerAdapter = MovieTrendingPagerAdapter(this)

        binding.pager.apply {
            adapter = pagerAdapter
            isUserInputEnabled = false
        }
    }

    private fun setupTabLayout() {
        binding.tabLayout.setSelectedTabIndicator(
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                R.drawable.bg_indicator_tab_layout
            } else {
                R.drawable.bg_indicator_tab_layout_full_width
            }
        )

        tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = resources.getStringArray(R.array.tab_movie_and_tv)[position]
        }.apply {
            attach()
        }
    }
}
