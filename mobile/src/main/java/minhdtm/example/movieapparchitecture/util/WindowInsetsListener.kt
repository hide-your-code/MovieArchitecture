package minhdtm.example.movieapparchitecture.util

import android.view.View
import android.view.WindowInsets
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import minhdtm.example.movieapparchitecture.extension.systemInsetsBottom
import minhdtm.example.movieapparchitecture.extension.systemInsetsTop

object NoopWindowInsetsListener : View.OnApplyWindowInsetsListener {
    override fun onApplyWindowInsets(v: View, insets: WindowInsets): WindowInsets = insets
}

object HeightTopWindowInsetsListener : View.OnApplyWindowInsetsListener {
    override fun onApplyWindowInsets(v: View, insets: WindowInsets): WindowInsets {
        val topInset = insets.systemInsetsTop()
        if (v.layoutParams.height != topInset) {
            v.layoutParams.height = topInset
            v.requestLayout()
        }
        return insets
    }
}

object HeightTopPaddingWindowInsetsListener : View.OnApplyWindowInsetsListener {
    override fun onApplyWindowInsets(v: View, insets: WindowInsets): WindowInsets {
        val topInset = insets.systemInsetsTop()
        val layoutParams = v.layoutParams as AppBarLayout.LayoutParams
        layoutParams.topMargin = topInset
        v.layoutParams = layoutParams
        return insets
    }
}

object HeightBottomPaddingWindowInsetsListener: View.OnApplyWindowInsetsListener {
    override fun onApplyWindowInsets(v: View, insets: WindowInsets): WindowInsets {
        val bottomInset = insets.systemInsetsBottom()
        v.setPadding(0, 0, 0, bottomInset)
        return insets
    }
}
