package minhdtm.example.movieapparchitecture.util

import android.view.View
import android.view.WindowInsets
import androidx.core.view.updatePadding
import minhdtm.example.movieapparchitecture.extension.systemInsetsBottom
import minhdtm.example.movieapparchitecture.extension.systemInsetsTop

object NoopWindowInsetsListener : View.OnApplyWindowInsetsListener {
    override fun onApplyWindowInsets(v: View, insets: WindowInsets): WindowInsets {
        v.updatePadding(
            top = insets.systemInsetsTop(),
            bottom = insets.systemInsetsBottom()
        )

        return insets
    }
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
