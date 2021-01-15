package minhdtm.example.movieapparchitecture

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import minhdtm.example.shared.analytics.AnalyticsHelper
import timber.log.Timber

@HiltAndroidApp
class MovieApplication : Application() {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
