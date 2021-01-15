package minhdtm.example.movieapparchitecture.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import minhdtm.example.movieapparchitecture.R

fun navigationItemBackground(context: Context): Drawable? {
    var background = AppCompatResources.getDrawable(context, R.drawable.bg_item_navigation)

    if (background != null) {
        val tint = AppCompatResources.getColorStateList(context, R.color.item_navigation_background_tint)
        background = DrawableCompat.wrap(background.mutate())
        background.setTintList(tint)
    }
    return background
}
