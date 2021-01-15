package minhdtm.example.movieapparchitecture.extension

import androidx.fragment.app.Fragment
import minhdtm.example.movieapparchitecture.util.AutoClearValue

fun <T : Any> Fragment.autoClear(): AutoClearValue<T> = AutoClearValue(this)
