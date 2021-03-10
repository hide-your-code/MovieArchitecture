package minhdtm.example.movieapparchitecture.util

object Constant {

    object Analytics {
        const val UI_EVENT_CONTENT_TYPE = "ui event"
        const val UI_ACTION_PARAM = "ui_action"
        const val USER_SIGNED_IN_PROPERTY = "user_signed_in"
        const val USER_REGISTERED_PROPERTY = "user_registered"
    }

    object ScreenName {
        //region Activity
        const val ACTIVITY_MAIN = "activity_main"
        const val ACTIVITY_LAUNCH = "activity_launch"
        const val ACTIVITY_ONBOARDING = "activity_onboarding"
        //endregion

        //region Fragment
        const val FRAGMENT_HOME = "home"
        const val FRAGMENT_WELCOME = "welcome"
        const val FRAGMENT_FIRST_ONBOARDING = "first_onboarding"
        const val FRAGMENT_SECOND_ONBOARDING = "second_onboarding"
        const val FRAGMENT_FINISH_ONBOARDING = "finish_onboarding"
        //endregion
    }
}
