package minhdtm.example.shared.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import minhdtm.example.shared.data.pref.PreferenceRepository
import minhdtm.example.shared.data.pref.SharedPreferenceStorage
import minhdtm.example.shared.data.remote.ApiClient
import minhdtm.example.shared.data.repository.DefaultGenresRepository
import minhdtm.example.shared.data.repository.DefaultMovieTrendingRepository
import minhdtm.example.shared.data.repository.GenresRepository
import minhdtm.example.shared.data.repository.MovieTrendingRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class SharedModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): PreferenceRepository =
        SharedPreferenceStorage(context)

    @Singleton
    @Provides
    fun provideGenresRepository(remote: ApiClient): GenresRepository = DefaultGenresRepository(remote)

    @Singleton
    @Provides
    fun provideMovieTrendingRepository(remote: ApiClient): MovieTrendingRepository =
        DefaultMovieTrendingRepository(remote)
}
