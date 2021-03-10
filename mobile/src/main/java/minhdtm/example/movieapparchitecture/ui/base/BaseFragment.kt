package minhdtm.example.movieapparchitecture.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.core.CoreFragment
import minhdtm.example.shared.analytics.AnalyticsHelper
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment<Binding : ViewDataBinding, VM : ViewModel> : CoreFragment<Binding, VM>() {

    @Inject
    lateinit var analytics: AnalyticsHelper

    abstract val screenName: String

    open val title: String? = ""

    open var navigationHost: NavigationHost? = null

    open val isVisibleSearchMenu: Boolean = true

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

        initView()
        bindViewModel()
    }

    open fun initView() {}

    open fun bindViewModel() {}

    private fun setupToolbar(view: View) {
        val host = navigationHost ?: return
        val mainToolbar: Toolbar = view.findViewById(R.id.toolbar) ?: return
        mainToolbar.apply {
            host.registerToolbarWithNavigation(this)

            val searchItem = mainToolbar.menu.findItem(R.id.menu_search)
            searchItem.isVisible = isVisibleSearchMenu

            title = if (this@BaseFragment.title.isNullOrBlank()) {
                ""
            } else {
                this@BaseFragment.title
            }
        }
    }
}
