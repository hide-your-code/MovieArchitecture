package minhdtm.example.movieapparchitecture.extension

import android.os.Build
import android.view.WindowInsets
import androidx.drawerlayout.widget.DrawerLayout

// In android Q and above, we need to ensure that the device isn't in gesture mode.
@Suppress("DEPRECATION")
fun DrawerLayout.shouldCloseFromBackPress(): Boolean = when {
    Build.VERSION.SDK_INT > Build.VERSION_CODES.Q -> {
        val insets = rootWindowInsets.getInsets(WindowInsets.Type.systemGestures())
        insets.left == 0 && insets.right == 0
    }
    Build.VERSION.SDK_INT == Build.VERSION_CODES.Q -> {
        val insets = rootWindowInsets.systemGestureInsets
        insets.left == 0 && insets.right == 0
    }
    else -> true
}
