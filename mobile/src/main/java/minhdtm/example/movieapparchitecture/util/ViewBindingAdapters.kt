package minhdtm.example.movieapparchitecture.util

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.imageview.ShapeableImageView
import minhdtm.example.movieapparchitecture.extension.addRadius
import timber.log.Timber

@BindingAdapter(value = ["imageUri", "placeholder", "radius"], requireAll = false)
fun imageUri(imageView: ShapeableImageView, imageUri: Uri?, placeholder: Drawable?, radius: Float) {
    when (imageUri) {
        null -> {
            Timber.d("Unsetting image url")
            Glide.with(imageView)
                .load(placeholder)
                .apply(RequestOptions().override(Target.SIZE_ORIGINAL))
                .into(imageView)
        }
        else -> {
            Glide.with(imageView)
                .load(imageUri)
                .apply(RequestOptions().placeholder(placeholder))
                .apply(RequestOptions().override(Target.SIZE_ORIGINAL))
                .into(imageView)
        }
    }

    imageView.addRadius(radius)
}

@BindingAdapter(value = ["imageUrl", "placeholder", "radius"], requireAll = false)
fun imageUrl(imageView: ShapeableImageView, imageUrl: String?, placeholder: Drawable?, radius: Float) {
    imageUri(imageView, imageUrl?.toUri(), placeholder, radius)
}
