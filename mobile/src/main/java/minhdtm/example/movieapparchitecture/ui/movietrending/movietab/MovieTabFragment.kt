package minhdtm.example.movieapparchitecture.ui.movietrending.movietab

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import dagger.hilt.android.AndroidEntryPoint
import minhdtm.example.model.MovieTrendingItem
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.databinding.FragmentMovieTabBinding
import minhdtm.example.movieapparchitecture.extension.attachSnapHelperWithListener
import minhdtm.example.movieapparchitecture.extension.autoClear
import minhdtm.example.movieapparchitecture.extension.eventObserve
import minhdtm.example.movieapparchitecture.extension.nonNullObserve
import minhdtm.example.movieapparchitecture.ui.adapter.GenreSize
import minhdtm.example.movieapparchitecture.ui.adapter.GenresAdapter
import minhdtm.example.movieapparchitecture.ui.adapter.MoviesAdapter
import minhdtm.example.movieapparchitecture.ui.base.BaseFragment
import minhdtm.example.movieapparchitecture.ui.movietrending.MovieTrendingFragmentDirections
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.FRAGMENT_MOVIE_TRENDING_POPULAR
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.FRAGMENT_MOVIE_TRENDING_TOP_RATED
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.FRAGMENT_MOVIE_TRENDING_UPCOMING
import minhdtm.example.movieapparchitecture.widget.snap.CenterZoomLayoutManagerHelper
import timber.log.Timber

enum class MovieTrendingTab(val type: Int) {
    POPULAR(0),
    TOP_RATED(1),
    UP_COMING(2)
}

@AndroidEntryPoint
class MovieTabFragment : BaseFragment<FragmentMovieTabBinding, MovieTabViewModel>() {

    private var type: Int? = null

    override val screenName: String
        get() = when (type) {
            MovieTrendingTab.UP_COMING.type -> FRAGMENT_MOVIE_TRENDING_UPCOMING
            MovieTrendingTab.TOP_RATED.type -> FRAGMENT_MOVIE_TRENDING_TOP_RATED
            else -> FRAGMENT_MOVIE_TRENDING_POPULAR
        }

    override val viewModel: MovieTabViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_movie_tab

    private var genreAdapter: GenresAdapter by autoClear()
    private var movieAdapter: MoviesAdapter by autoClear()

    private var snapHelper: SnapHelper by autoClear()
    private var layoutManager: CenterZoomLayoutManagerHelper by autoClear()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            type = it.getInt(KEY_POSITION)
            viewModel.getMovieTrending(it.getInt(KEY_POSITION))
        }
    }

    override fun initView(view: View) {
        super.initView(view)
        setupGenre()
        setupListMovie()
    }

    override fun bindViewModel() {
        viewModel.run {
            genres.nonNullObserve(viewLifecycleOwner) {
                genreAdapter.submitList(it)
            }

            movies.nonNullObserve(viewLifecycleOwner) {
                movieAdapter.submitList(it)
            }

            itemClick.eventObserve(viewLifecycleOwner, ::navigateMovieDetail)
        }
    }

    private fun setupGenre() {
        genreAdapter = GenresAdapter(GenreSize.MEDIUM)
        binding.rvGenre.adapter = genreAdapter
    }

    private fun setupListMovie() {
        movieAdapter = MoviesAdapter {
            viewModel.onClickItem(it)
        }
        snapHelper = LinearSnapHelper()
        layoutManager = CenterZoomLayoutManagerHelper(requireContext(), RecyclerView.HORIZONTAL, false)

        binding.rvMovie.run {
            adapter = movieAdapter

            // For scroll zoom in center position, use CenterZoomLayoutManagerHelper
            layoutManager = this@MovieTabFragment.layoutManager

            attachSnapHelperWithListener(snapHelper) { position ->
                viewModel.getMovieFromPosition(position)
            }
        }
    }

    private fun navigateMovieDetail(item: MovieTrendingItem) {
        val action = MovieTrendingFragmentDirections.toMovieDetail(item.id)
        findNavController().navigate(action)
    }

    companion object {
        fun newInstance(position: Int): MovieTabFragment = MovieTabFragment().apply {
            arguments = bundleOf(KEY_POSITION to position)
        }

        private const val KEY_POSITION = "position"
    }
}
