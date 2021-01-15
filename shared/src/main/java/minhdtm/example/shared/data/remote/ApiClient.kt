package minhdtm.example.shared.data.remote

import minhdtm.example.model.Genres
import minhdtm.example.shared.BuildConfig
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("genre/movie/list?api_key=${BuildConfig.API_KEY}")
    suspend fun getPost(): Response<Genres>
}
