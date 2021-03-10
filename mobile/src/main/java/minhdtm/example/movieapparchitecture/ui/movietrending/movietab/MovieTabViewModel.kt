package minhdtm.example.movieapparchitecture.ui.movietrending.movietab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import minhdtm.example.model.Genre
import minhdtm.example.model.MovieTrendingItem
import minhdtm.example.movieapparchitecture.ui.base.BaseViewModel
import minhdtm.example.shared.domain.genre.GetGenresUseCase
import minhdtm.example.shared.domain.movie.GetMovieTrendingUseCase
import minhdtm.example.shared.result.Event
import javax.inject.Inject

@HiltViewModel
class MovieTabViewModel @Inject constructor(
    genresUseCase: GetGenresUseCase,
    private val moviePopularUseCase: GetMovieTrendingUseCase
) : BaseViewModel() {

    private val _position = MutableLiveData<Int>()

    private val _itemPosition = MutableLiveData<MovieTrendingItem>()
    val itemPosition: LiveData<MovieTrendingItem> = _itemPosition

    private val _itemClick = MutableLiveData<Event<MovieTrendingItem>>()
    val itemClick: LiveData<Event<MovieTrendingItem>> = _itemClick

    val genres: LiveData<List<Genre>> = liveData {
        genresUseCase(Unit).collectViewModel {
            emit(it.genres)
        }
    }

    val movies = _position.switchMap {
        liveData {
            moviePopularUseCase(it).collectViewModel { result ->
                emit(result)
            }
        }
    }

    fun getMovieFromPosition(position: Int) {
        if (!movies.value.isNullOrEmpty()) {
            _itemPosition.value = movies.value?.get(position)
        }
    }

    fun getMovieTrending(position: Int) {
        _position.value = position
    }

    fun onClickItem(item: MovieTrendingItem) {
        _itemClick.value = Event(item)
    }
}
