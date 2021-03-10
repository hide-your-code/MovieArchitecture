package minhdtm.example.shared.data.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import minhdtm.example.model.Genres
import minhdtm.example.shared.data.remote.ApiClient
import minhdtm.example.shared.result.Result
import minhdtm.example.shared.result.succeeded
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito

//@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
//class GenresRepositoryTest {
//
//    @Mock
//    private lateinit var remote: ApiClient
//
//    private lateinit var genresRepository: GenresRepository
//
//    @Before
//    fun setup() {
//        genresRepository = DefaultGenresRepository(remote)
//    }
//
//    @Test
//    fun `test`() {
//        runBlocking {
//            Mockito.`when`(remote.getGenres()).thenReturn(true)
//            assertThat(genresRepository.getGenres().first { it.succeeded }, `is`(Result.Success(Genres())))
//        }
//    }
//}