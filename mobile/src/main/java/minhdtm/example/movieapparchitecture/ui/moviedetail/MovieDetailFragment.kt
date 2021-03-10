package minhdtm.example.movieapparchitecture.ui.moviedetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.databinding.FragmentMovieDetailBinding
import minhdtm.example.movieapparchitecture.extension.autoClear
import minhdtm.example.movieapparchitecture.extension.nonNullObserve
import minhdtm.example.movieapparchitecture.ui.adapter.CastAdapter
import minhdtm.example.movieapparchitecture.ui.adapter.GenreSize
import minhdtm.example.movieapparchitecture.ui.adapter.GenresAdapter
import minhdtm.example.movieapparchitecture.ui.base.BaseFragment
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.FRAGMENT_MOVIE_DETAIL
import minhdtm.example.movieapparchitecture.util.HeightBottomPaddingWindowInsetsListener
import minhdtm.example.movieapparchitecture.util.HeightTopPaddingWindowInsetsListener
import timber.log.Timber

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {

    override val screenName: String = FRAGMENT_MOVIE_DETAIL

    override val viewModel: MovieDetailViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_movie_detail

    private var genresAdapter: GenresAdapter by autoClear()
    private var castAdapter: CastAdapter by autoClear()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val id = it.getInt(KEY_ID)
            viewModel.getMovieInfo(id)
        }
    }

    override fun initView(view: View) {
        super.initView(view)

        binding.appBar.outlineProvider = null
        binding.toolbar.setOnApplyWindowInsetsListener(HeightTopPaddingWindowInsetsListener)
        binding.svContent.setOnApplyWindowInsetsListener(HeightBottomPaddingWindowInsetsListener)

        setupGenre()
        setupCastAndCrew()
    }

    override fun bindViewModel() {
        super.bindViewModel()

        viewModel.run {
            movie.nonNullObserve(viewLifecycleOwner) {
                genresAdapter.submitList(it.genres)

                setTextRuntime(it.runtime)

                val percent = it.voteAverage.times(PERCENT)
                binding.progressBar.setProgressWithAnimation(percent)
            }

            cast.nonNullObserve(viewLifecycleOwner) {
                castAdapter.submitList(it.take(SIZE_CAST))
            }

            error.nonNullObserve(viewLifecycleOwner) {
                Timber.d("Error: ${it.statusMessage}")
            }

            loading.nonNullObserve(viewLifecycleOwner) {
                binding.pbLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setupGenre() {
        genresAdapter = GenresAdapter(GenreSize.SMALL)
        binding.rvGenre.adapter = genresAdapter
    }

    private fun setupCastAndCrew() {
        castAdapter = CastAdapter()
        binding.rvCast.adapter = castAdapter
    }

    private fun setTextRuntime(minute: Int) {
        val hour = minute.div(HOUR_PER_MINUTE)
        val min = minute - hour.times(HOUR_PER_MINUTE)
        binding.tvRuntime.text = getString(R.string.movie_detail_runtime, hour.toString(), min.toString())
    }

    companion object {
        const val KEY_ID = "id"

        private const val HOUR_PER_MINUTE = 60
        private const val PERCENT = 10
        private const val SIZE_CAST = 8
    }
}
