package minhdtm.example.shared.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import minhdtm.example.shared.data.pref.PreferenceRepository
import minhdtm.example.shared.data.pref.SharedPreferenceStorage
import minhdtm.example.shared.data.remote.ApiClient
import minhdtm.example.shared.data.repository.DefaultGenresRepository
import minhdtm.example.shared.data.repository.DefaultMovieRepository
import minhdtm.example.shared.data.repository.GenresRepository
import minhdtm.example.shared.data.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): PreferenceRepository =
        SharedPreferenceStorage(context)

    @Singleton
    @Provides
    fun provideGenresRepository(remote: ApiClient, gson: Gson): GenresRepository = DefaultGenresRepository(remote, gson)

    @Singleton
    @Provides
    fun provideMovieRepository(remote: ApiClient, gson: Gson): MovieRepository = DefaultMovieRepository(remote, gson)
}
