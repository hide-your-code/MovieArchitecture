package minhdtm.example.movieapparchitecture.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import minhdtm.example.shared.result.Event

fun <T> LiveData<Event<T>>.eventObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner) { it?.getContentIfNotHandled()?.let(observer) }
}

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner) { it?.let(observer) }
}
