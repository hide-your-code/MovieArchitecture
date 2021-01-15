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
        // Movie Trending
        const val FRAGMENT_MOVIE_TRENDING = "movie_trending"
        const val FRAGMENT_MOVIE_TRENDING_UPCOMING = "movie_trending_upcoming"
        const val FRAGMENT_MOVIE_TRENDING_POPULAR = "movie_trending_popular"
        const val FRAGMENT_MOVIE_TRENDING_TOP_RATED = "movie_trending_top_rated"

        // Movie Detail
        const val FRAGMENT_MOVIE_DETAIL = "movie_detail"

        // Onboarding
        const val FRAGMENT_WELCOME = "welcome"
        const val FRAGMENT_FIRST_ONBOARDING = "first_onboarding"
        const val FRAGMENT_SECOND_ONBOARDING = "second_onboarding"
        const val FRAGMENT_FINISH_ONBOARDING = "finish_onboarding"
        //endregion
    }
}
