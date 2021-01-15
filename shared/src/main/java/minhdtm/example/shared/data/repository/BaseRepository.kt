package minhdtm.example.shared.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.FlowCollector
import minhdtm.example.model.ErrorResponse
import minhdtm.example.shared.result.Result
import retrofit2.Response

open class BaseRepository(private val gson: Gson) {

    open suspend fun <T, R> FlowCollector<Result<R>>.emitData(dataRemote: Response<T>, mapper: (T) -> R) {
        emit(Result.Loading)

        if (dataRemote.isSuccessful) {
            dataRemote.body()?.let {
                emit(Result.Success(mapper.invoke(it)))
            } ?: run {
                Result.Error(ErrorResponse(statusMessage = ErrorResponse.MESS_EMPTY_RESPONSE))
            }
        } else {
            val errorResponse = dataRemote.errorBody()?.charStream()
            val error: ErrorResponse = gson.fromJson(errorResponse, object : TypeToken<ErrorResponse>() {}.type)
            emit(Result.Error(error))
        }
    }
}
