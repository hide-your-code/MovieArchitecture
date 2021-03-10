package minhdtm.example.movieapparchitecture.ui.base

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.core.CoreFragment
import minhdtm.example.movieapparchitecture.extension.nonNullObserve
import minhdtm.example.movieapparchitecture.extension.showIf
import minhdtm.example.movieapparchitecture.ui.MainActivity
import minhdtm.example.shared.analytics.AnalyticsHelper
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment<Binding : ViewDataBinding, VM : BaseViewModel> : CoreFragment<Binding, VM>() {

    @Inject
    lateinit var analytics: AnalyticsHelper

    abstract val screenName: String

    open val title: String? = null

    open val isVisibleSearchMenu: Boolean = true

    private var vToolbar: Toolbar? = null
    private var vLoading: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate: ${javaClass.simpleName}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        analytics.sendScreenView(screenName, requireActivity(), this)

        initView(view)
        setupToolbar()

        bindViewModel()
    }

    override fun onDestroyView() {
        vLoading = null
        vToolbar = null
        super.onDestroyView()
    }

    open fun initView(view: View) {
        vLoading = view.findViewById(R.id.pb_loading)
        vToolbar = view.findViewById(R.id.toolbar)
    }

    open fun bindViewModel() {
        viewModel.run {
            error.nonNullObserve(viewLifecycleOwner) {
                Timber.d("Error: ${it.statusMessage}")
            }

            loading.nonNullObserve(viewLifecycleOwner) {
                vLoading?.showIf(it)
            }
        }
    }

    private fun setupToolbar() {
        vToolbar?.let {
            (requireActivity() as MainActivity).registerToolbarWithNavigation(it)
        }
    }
}
