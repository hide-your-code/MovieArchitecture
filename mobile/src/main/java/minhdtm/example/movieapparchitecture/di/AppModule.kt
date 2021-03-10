package minhdtm.example.movieapparchitecture.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import minhdtm.example.shared.util.GsonHelper

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideGson(): Gson = GsonHelper.create()
}
