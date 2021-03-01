package minhdtm.example.movieapparchitecture.extension

import com.google.android.material.imageview.ShapeableImageView

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
