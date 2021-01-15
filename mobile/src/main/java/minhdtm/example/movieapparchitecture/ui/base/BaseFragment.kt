package minhdtm.example.movieapparchitecture.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.core.CoreFragment
import minhdtm.example.movieapparchitecture.extension.autoClear
import minhdtm.example.shared.analytics.AnalyticsHelper
import timber.log.Timber

abstract class BaseFragment<Binding : ViewDataBinding, VM : ViewModel> : CoreFragment<Binding, VM>() {

    @Inject
    lateinit var analytics: AnalyticsHelper

    abstract val screenName: String

    open var navigationHost: NavigationHost? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationHost) {
            navigationHost = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate: ${javaClass.simpleName}")
    }

    override fun onDetach() {
        super.onDetach()
        navigationHost = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        analytics.sendScreenView(screenName, requireActivity(), this)

        setupToolbar(view)
    }

    private fun setupToolbar(view: View) {
        val host = navigationHost ?: return
        val mainToolbar: Toolbar = view.findViewById(R.id.toolbar) ?: return
        mainToolbar.apply {
            host.registerToolbarWithNavigation(this)
        }
    }
}
