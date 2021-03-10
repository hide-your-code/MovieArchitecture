package minhdtm.example.movieapparchitecture.util

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView
import minhdtm.example.movieapparchitecture.extension.addRadius
import minhdtm.example.movieapparchitecture.extension.imageUri

@BindingAdapter(
    value = ["imageUri", "placeholder"],
    requireAll = false
)
fun imageUri(
    imageView: ShapeableImageView,
    imageUri: Uri?,
    placeholder: Drawable?
) {
    imageView.imageUri(imageUri, placeholder)
}

@BindingAdapter(
    value = ["imageUrl", "placeholder"],
    requireAll = false
)
fun imageUrl(
    imageView: ShapeableImageView,
    imageUrl: String?,
    placeholder: Drawable?
) = imageUri(imageView, imageUrl?.toUri(), placeholder)

@BindingAdapter(
    value = ["radius", "radiusTopLeft", "radiusTopRight", "radiusBottomLeft", "radiusBottomRight"],
    requireAll = false
)
fun imageRadius(
    imageView: ShapeableImageView,
    radius: Float?,
    radiusTopLeft: Float?,
    radiusTopRight: Float?,
    radiusBottomLeft: Float?,
    radiusBottomRight: Float?
) {
    when {
        radius != null -> {
            imageView.addRadius(radius)
        }
        radiusTopLeft != null || radiusTopRight != null || radiusBottomLeft != null || radiusBottomRight != null -> {
            imageView.addRadius(radiusTopLeft, radiusTopRight, radiusBottomLeft, radiusBottomRight)
        }
        else -> {
            return
        }
    }
}
