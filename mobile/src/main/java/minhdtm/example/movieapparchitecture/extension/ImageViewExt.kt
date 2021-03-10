package minhdtm.example.movieapparchitecture.extension

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.imageview.ShapeableImageView
import timber.log.Timber

fun ShapeableImageView.addRadius(radius: Float) {
    val shapeAppearance = shapeAppearanceModel.toBuilder()
        .setAllCornerSizes(radius)
        .build()
    shapeAppearanceModel = shapeAppearance
}

fun ShapeableImageView.addRadius(topLeft: Float?, topRight: Float?, bottomLeft: Float?, bottomRight: Float?) {
    val shapeAppearance = shapeAppearanceModel.toBuilder()
        .setTopLeftCornerSize(topLeft ?: 0f)
        .setTopRightCornerSize(topRight ?: 0f)
        .setBottomLeftCornerSize(bottomLeft ?: 0f)
        .setBottomRightCornerSize(bottomRight ?: 0f)
        .build()
    shapeAppearanceModel = shapeAppearance
}

fun ShapeableImageView.imageUrl(imageUrl: String?, placeholder: Drawable? = null) {
    imageUri(imageUrl?.toUri(), placeholder)
}

fun ShapeableImageView.imageUri(imageUri: Uri?, placeholder: Drawable? = null) {
    when (imageUri) {
        null -> {
            Timber.d("Unsetting image url")
            Glide.with(this)
                .load(placeholder)
                .apply(RequestOptions().override(Target.SIZE_ORIGINAL))
                .into(this)
        }
        else -> {
            Glide.with(this)
                .load(imageUri)
                .apply(RequestOptions().placeholder(placeholder))
                .apply(RequestOptions().override(Target.SIZE_ORIGINAL))
                .into(this)
        }
    }
}
