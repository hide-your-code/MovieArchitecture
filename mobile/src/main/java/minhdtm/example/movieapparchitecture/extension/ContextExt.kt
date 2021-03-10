package minhdtm.example.movieapparchitecture.extension

import android.content.Context
import android.util.TypedValue

fun Context.spToPx(sp: Float): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)
