package minhdtm.example.shared.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import minhdtm.example.shared.BuildConfig
import minhdtm.example.shared.data.local.LocalDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ): LocalDatabase = Room.databaseBuilder(context, LocalDatabase::class.java, BuildConfig.DATABASE_NAME).build()
}
