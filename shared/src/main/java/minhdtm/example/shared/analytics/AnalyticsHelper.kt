package minhdtm.example.shared.analytics

import android.app.Activity
import androidx.fragment.app.Fragment

/** Analytics API surface */
interface AnalyticsHelper {

    /** Set the user signed in property */
    fun sendScreenView(screenName: String, activity: Activity, fragment: Fragment?)

    /** Record a UI event.E.g. user clicks a button */
    fun logUiEvent(itemId: String, action: String)

    /** Set the user signed in property */
    fun setUserSignedIn(isSignedIn: Boolean)

    /** Set the user registered in property */
    fun setUserRegistered(isRegistered: Boolean)
}
