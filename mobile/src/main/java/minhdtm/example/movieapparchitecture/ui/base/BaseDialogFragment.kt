package minhdtm.example.movieapparchitecture.ui.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import minhdtm.example.movieapparchitecture.core.CoreDialogFragment
import minhdtm.example.shared.analytics.AnalyticsHelper
import timber.log.Timber

abstract class BaseDialogFragment<Binding : ViewDataBinding, VM : ViewModel> : CoreDialogFragment<Binding, VM>() {

    @Inject
    lateinit var analytics: AnalyticsHelper

    abstract val screenName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate: ${javaClass.simpleName}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.sendScreenView(screenName, requireActivity(), this)
    }
}
