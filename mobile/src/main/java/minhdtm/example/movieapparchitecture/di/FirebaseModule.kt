package minhdtm.example.movieapparchitecture.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import minhdtm.example.movieapparchitecture.util.helper.FirebaseAnalyticsHelper
import minhdtm.example.shared.analytics.AnalyticsHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAnalytics(): FirebaseAnalytics = Firebase.analytics

    @Provides
    @Singleton
    fun provideFirebaseCrashlytics(): FirebaseCrashlytics = Firebase.crashlytics

    @Provides
    fun provideFirebaseAnalyticsHelper(
        firebaseAnalytics: FirebaseAnalytics,
        firebaseCrashlytics: FirebaseCrashlytics
    ): AnalyticsHelper = FirebaseAnalyticsHelper(firebaseAnalytics, firebaseCrashlytics)
}
