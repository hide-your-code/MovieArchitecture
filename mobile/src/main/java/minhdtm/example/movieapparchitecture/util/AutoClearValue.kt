package minhdtm.example.movieapparchitecture.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoClearValue<T : Any>(fragment: Fragment) : ReadWriteProperty<Fragment, T> {

    private var currentValue: T? = null

    init {
        fragment.lifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreate() {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) {
                    it.lifecycle.addObserver(object : LifecycleObserver {

                        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                        fun onDestroy() {
                            currentValue = null
                        }
                    })
                }
            }
        })
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        currentValue = value
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        currentValue ?: throw IllegalArgumentException(errorMessage)

    companion object {
        private const val errorMessage = "Auto-cleared-value is not available"
    }
}
