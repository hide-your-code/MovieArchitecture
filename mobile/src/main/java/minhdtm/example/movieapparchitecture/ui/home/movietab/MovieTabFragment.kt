package minhdtm.example.movieapparchitecture.ui.home.movietab

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import dagger.hilt.android.AndroidEntryPoint
import minhdtm.example.model.MovieTrendingItem
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.databinding.FragmentMovieTabBinding
import minhdtm.example.movieapparchitecture.extension.attachSnapHelperWithListener
import minhdtm.example.movieapparchitecture.extension.autoClear
import minhdtm.example.movieapparchitecture.extension.nonNullObserve
import minhdtm.example.movieapparchitecture.ui.base.BaseFragment
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.FRAGMENT_HOME_MOVIE_TAB
import minhdtm.example.movieapparchitecture.util.helper.snap.CenterZoomLayoutManagerHelper

@AndroidEntryPoint
class MovieTabFragment : BaseFragment<FragmentMovieTabBinding, MovieTabViewModel>() {

    override val screenName: String = FRAGMENT_HOME_MOVIE_TAB

    override val viewModel: MovieTabViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_movie_tab

    private var genreAdapter: MovieTabGenreAdapter by autoClear()
    private var movieAdapter: MoviesAdapter by autoClear()

    private var snapHelper: SnapHelper by autoClear()
    private var layoutManager: CenterZoomLayoutManagerHelper by autoClear()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            viewModel.getMovieTrending(it.getInt(KEY_POSITION))
        }
    }

    override fun initView() {
        super.initView()

        setupGenre()
        setupListMovie()
    }

    override fun bindViewModel() {
        viewModel.apply {
            genres.nonNullObserve(viewLifecycleOwner) {
                genreAdapter.submitList(it)
            }
            movies.nonNullObserve(viewLifecycleOwner) { data ->
                movieAdapter.submitList(data)
            }
            itemPosition.nonNullObserve(viewLifecycleOwner, ::setInfoMovie)
        }
    }

    private fun setupGenre() {
        genreAdapter = MovieTabGenreAdapter()
        binding.rvGenre.adapter = genreAdapter
    }

    private fun setupListMovie() {
        movieAdapter = MoviesAdapter()
        snapHelper = LinearSnapHelper()
        layoutManager = CenterZoomLayoutManagerHelper(requireContext(), RecyclerView.HORIZONTAL, false)

        binding.rvMovie.apply {
            adapter = movieAdapter

            // For scroll zoom in center position, use CenterZoomLayoutManagerHelper
            layoutManager = this@MovieTabFragment.layoutManager

            attachSnapHelperWithListener(snapHelper) { position ->
                viewModel.getMovieFromPosition(position)
            }
        }
    }

    private fun setInfoMovie(item: MovieTrendingItem) {
        binding.tvName.text = item.title
        binding.tvVoteAverage.text = item.voteAverage.toString()
    }

    companion object {
        fun newInstance(position: Int): MovieTabFragment = MovieTabFragment().apply {
            arguments = bundleOf(KEY_POSITION to position)
        }

        private const val KEY_POSITION = "position"
    }
}
