package minhdtm.example.movieapparchitecture.extension

import android.view.View
import android.view.View.OnAttachStateChangeListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private fun createStateForView(view: View) = ViewPaddingState(view.paddingLeft,
    view.paddingTop, view.paddingRight, view.paddingBottom, view.paddingStart, view.paddingEnd)

fun View.doOnApplyWindowInserts(f: (View, WindowInsetsCompat, ViewPaddingState) -> Unit) {
    val paddingState = createStateForView(this)
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsetsCompat ->
        f(view, windowInsetsCompat, paddingState)
        windowInsetsCompat
    }
    requestApplyWhenAttached()
}

fun View.requestApplyWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

fun View.isRtl() = layoutDirection == View.LAYOUT_DIRECTION_RTL

data class ViewPaddingState(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
    val start: Int,
    val end: Int
)
