package minhdtm.example.shared.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import minhdtm.example.shared.data.pref.PreferenceRepository
import minhdtm.example.shared.data.pref.SharedPreferenceStorage
import minhdtm.example.shared.data.remote.ApiClient
import minhdtm.example.shared.data.repository.DefaultPostRepository
import minhdtm.example.shared.data.repository.PostRepository

@Module
@InstallIn(ApplicationComponent::class)
class SharedModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): PreferenceRepository =
        SharedPreferenceStorage(context)

    @Singleton
    @Provides
    fun providePostRepository(apiClient: ApiClient): PostRepository = DefaultPostRepository(apiClient)
}
