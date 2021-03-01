package minhdtm.example.movieapparchitecture.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import minhdtm.example.movieapparchitecture.ui.home.movietab.MovieTabFragment

class MovieTrendingPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = AMOUNT_TAB

    override fun createFragment(position: Int): Fragment = MovieTabFragment.newInstance(position)

    companion object {
        const val AMOUNT_TAB = 3
    }
}
