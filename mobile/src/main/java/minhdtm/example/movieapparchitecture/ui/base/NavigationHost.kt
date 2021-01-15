package minhdtm.example.movieapparchitecture.ui.base

import androidx.appcompat.widget.Toolbar

/** To be implemented by components that host top-level navigation destinations. */
interface NavigationHost {

    /** Called by BaseFragment to setup it's toolbar with the navigation controller. */
    fun registerToolbarWithNavigation(toolbar: Toolbar)
}
