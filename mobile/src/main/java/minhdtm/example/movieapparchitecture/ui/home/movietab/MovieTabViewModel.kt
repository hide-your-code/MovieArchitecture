package minhdtm.example.movieapparchitecture.ui.home.movietab

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import kotlinx.coroutines.flow.map
import minhdtm.example.model.MovieTrendingItem
import minhdtm.example.shared.domain.genre.GetGenresUseCase
import minhdtm.example.shared.domain.movietrending.GetMovieTrendingUseCase
import minhdtm.example.shared.result.data

class MovieTabViewModel @ViewModelInject constructor(
    genresUseCase: GetGenresUseCase,
    private val moviePopularUseCase: GetMovieTrendingUseCase
) : ViewModel() {

    private val _position = MutableLiveData<Int>()

    private val _itemPosition = MutableLiveData<MovieTrendingItem>()
    val itemPosition: LiveData<MovieTrendingItem> = _itemPosition

    val genres = genresUseCase(Unit).map {
        it.data?.genres
    }.asLiveData()

    val movies = _position.switchMap {
        moviePopularUseCase(it).map { result ->
            result.data
        }.asLiveData()
    }

    fun getMovieFromPosition(position: Int) {
        if (!movies.value.isNullOrEmpty()) {
            _itemPosition.value = movies.value?.get(position)
        }
    }

    fun getMovieTrending(position: Int) {
        _position.value = position
    }
}
