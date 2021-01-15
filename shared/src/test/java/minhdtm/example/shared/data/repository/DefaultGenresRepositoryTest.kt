package minhdtm.example.shared.data.repository

import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import minhdtm.example.model.ErrorResponse
import minhdtm.example.model.Genres
import minhdtm.example.shared.result.Result
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class GenresRepositoryTest : BaseTestRepository<DefaultGenresRepository>() {

    override fun setup() {
        super.setup()

        repository = spyk(DefaultGenresRepository(remote, gson), recordPrivateCalls = true)
    }

    @Test
    fun `get genres_remote return response success`() = runBlocking {
        val response = Genres()
        val exception = ErrorResponse()

        coEvery { remote.getGenres() } returns Response.success(response)

        assertThat(repository.getGenres().first(), `is`(Result.Loading))
        assertThat(repository.getGenres().emitSecond(), `is`(Result.Success(response)))

        assertThat(repository.getGenres().count(), `is`(2))
    }

    @Test
    fun `get genres_remote return response error`() = runBlocking {

        val responseBody = ResponseBody.charStream()

        coEvery { remote.getGenres() } returns Response.error(404, ErrorResponse)
    }
}