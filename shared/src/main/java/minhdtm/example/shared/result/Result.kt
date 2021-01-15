package minhdtm.example.shared.result

import minhdtm.example.model.ErrorResponse
import minhdtm.example.shared.result.Result.Success
import minhdtm.example.shared.result.Result.Error

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val exception: ErrorResponse) : Result<Nothing>()

    object Loading : Result<Nothing>()

    override fun toString(): String = when (this) {
        is Success<*> -> "Success[data=$data]"
        is Error -> "Error[exception=$exception]"
        Loading -> "Loading"
    }
}

val Result<*>.succeeded
    get() = this is Success && data != null

val <T> Result<T>.data: T
    get() = (this as Success).data

val <T> Result<T>.exception: ErrorResponse
    get() = (this as Error).exception

fun <T> Result<T>.successOr(fallback: T): T = (this as? Success<T>)?.data ?: fallback


