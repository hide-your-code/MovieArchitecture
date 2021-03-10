package minhdtm.example.movieapparchitecture.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import minhdtm.example.model.ErrorResponse
import minhdtm.example.movieapparchitecture.extension.checkAllMatched
import minhdtm.example.shared.result.Result

open class BaseViewModel : ViewModel() {

    private val _error = MutableLiveData<ErrorResponse>()
    val error: LiveData<ErrorResponse> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    open suspend fun <T> Flow<Result<T>>.collectViewModel(
        onError: ((ErrorResponse) -> Unit)? = null,
        isLoading: Boolean = true,
        onSuccess: suspend (T) -> Unit
    ) {
        collect {
            when (it) {
                is Result.Success -> {
                    showHideLoading(false)
                    onSuccess.invoke(it.data)
                }
                is Result.Error -> {
                    showHideLoading(false)
                    _error.value = it.exception
                    onError?.invoke(it.exception)
                }
                is Result.Loading -> {
                    showHideLoading(true)
                }
            }.checkAllMatched
        }
    }

    private fun showHideLoading(isLoading: Boolean) {
        if (_loading.value != isLoading) {
            _loading.value = isLoading
        }
    }
}
