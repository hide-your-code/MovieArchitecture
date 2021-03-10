package minhdtm.example.movieapparchitecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import minhdtm.example.shared.di.DefaultDispatcher
import minhdtm.example.shared.di.IoDispatcher
import minhdtm.example.shared.di.MainDispatcher
import minhdtm.example.shared.di.MainImmediateDispatcher

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @DefaultDispatcher
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainImmediateDispatcher
    @Provides
    fun provideMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
}
