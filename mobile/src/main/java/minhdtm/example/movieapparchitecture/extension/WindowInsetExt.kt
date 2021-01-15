package minhdtm.example.movieapparchitecture.extension

import android.os.Build
import android.view.WindowInsets
import android.view.WindowInsets.Type

@Suppress("DEPRECATION")
fun WindowInsets.systemInsetsLeft(): Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
    getInsets(Type.systemBars()).left
} else {
    systemWindowInsetLeft
}

@Suppress("DEPRECATION")
fun WindowInsets.systemInsetsTop(): Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
    getInsets(Type.systemBars()).top
} else {
    systemWindowInsetTop
}

@Suppress("DEPRECATION")
fun WindowInsets.systemInsetsRight(): Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
    getInsets(Type.systemBars()).right
} else {
    systemWindowInsetRight
}

@Suppress("DEPRECATION")
fun WindowInsets.systemInsetsBottom(): Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
    getInsets(Type.systemBars()).bottom
} else {
    systemWindowInsetBottom
}
