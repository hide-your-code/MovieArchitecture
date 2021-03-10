package minhdtm.example.movieapparchitecture.ui.movietrending

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import minhdtm.example.movieapparchitecture.ui.movietrending.movietab.MovieTabFragment

class MovieTrendingPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = AMOUNT_TAB

    override fun createFragment(position: Int): Fragment = MovieTabFragment.newInstance(position)

    companion object {
        const val AMOUNT_TAB = 3
    }
}
