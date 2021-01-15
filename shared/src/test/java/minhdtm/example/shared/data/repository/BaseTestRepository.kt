package minhdtm.example.shared.data.repository

import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import minhdtm.example.shared.data.pref.PreferenceRepository
import minhdtm.example.shared.data.remote.ApiClient
import minhdtm.example.shared.result.Result
import org.junit.Before

open class BaseTestRepository<T : Any> {

    lateinit var repository: T

    @MockK
    lateinit var remote: ApiClient

    @MockK
    lateinit var local: PreferenceRepository

    @MockK
    lateinit var gson: Gson

    @Before
    open fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    // Take the second emit
    suspend fun <R : Any> Flow<Result<R>>.emitSecond() = drop(1).first()

    suspend fun <R : Any> Flow<Result<R>>.emitThird() = drop(2).first()
}