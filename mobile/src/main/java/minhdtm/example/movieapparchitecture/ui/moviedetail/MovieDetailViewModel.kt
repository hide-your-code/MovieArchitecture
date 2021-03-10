package minhdtm.example.movieapparchitecture.ui.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import minhdtm.example.movieapparchitecture.ui.base.BaseViewModel
import minhdtm.example.shared.domain.movie.GetMovieCastUseCase
import minhdtm.example.shared.domain.movie.GetMovieUseCase
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    getMovieUseCase: GetMovieUseCase,
    getMovieCastUseCase: GetMovieCastUseCase
) : BaseViewModel() {

    private val _id = MutableLiveData<Int>()

    val movie = _id.switchMap {
        liveData {
            getMovieUseCase(it).collectViewModel {
                emit(it)
            }
        }
    }

    val cast = _id.switchMap { id ->
        liveData {
            getMovieCastUseCase(id).collectViewModel {
                emit(it)
            }
        }
    }

    fun getMovieInfo(id: Int) {
        if (_id.value != id) {
            _id.value = id
        }
    }
}
