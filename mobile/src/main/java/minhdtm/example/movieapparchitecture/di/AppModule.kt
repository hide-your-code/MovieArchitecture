package minhdtm.example.movieapparchitecture.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import minhdtm.example.shared.util.GsonHelper

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Provides
    fun provideGson(): Gson = GsonHelper.create()
}
