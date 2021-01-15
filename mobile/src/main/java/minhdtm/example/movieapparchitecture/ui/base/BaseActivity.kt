package minhdtm.example.movieapparchitecture.ui.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import minhdtm.example.movieapparchitecture.core.CoreActivity
import minhdtm.example.shared.analytics.AnalyticsHelper
import timber.log.Timber

abstract class BaseActivity<ViewBinding : ViewDataBinding, VM : ViewModel> : CoreActivity<ViewBinding, VM>() {

    @Inject
    lateinit var analytics: AnalyticsHelper

    abstract val screenName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("onCreate: ${javaClass.simpleName}")
        analytics.sendScreenView(screenName, this, null)
    }
}
