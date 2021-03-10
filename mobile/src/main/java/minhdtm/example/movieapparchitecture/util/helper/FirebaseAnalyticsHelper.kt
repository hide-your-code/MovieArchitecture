package minhdtm.example.movieapparchitecture.util.helper

import android.app.Activity
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject
import minhdtm.example.movieapparchitecture.util.Constant
import minhdtm.example.shared.analytics.AnalyticsHelper

class FirebaseAnalyticsHelper @Inject constructor(
    private val analytics: FirebaseAnalytics,
    private val crashlytics: FirebaseCrashlytics
) : AnalyticsHelper {

    override fun sendScreenView(screenName: String, activity: Activity, fragment: Fragment?) {
        analytics.run {
            logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
                param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
                param(FirebaseAnalytics.Param.SCREEN_CLASS, activity.localClassName)
            }
        }

        crashlytics.log("screen_name: $screenName")
    }

    override fun logUiEvent(itemId: String, action: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, itemId)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, Constant.Analytics.UI_EVENT_CONTENT_TYPE)
            param(Constant.Analytics.UI_ACTION_PARAM, action)
        }
    }

    override fun setUserSignedIn(isSignedIn: Boolean) {
        analytics.setUserProperty(
            Constant.Analytics.USER_SIGNED_IN_PROPERTY,
            isSignedIn.toString()
        )
    }

    override fun setUserRegistered(isRegistered: Boolean) {
        analytics.setUserProperty(
            Constant.Analytics.USER_REGISTERED_PROPERTY,
            isRegistered.toString()
        )
    }
}
