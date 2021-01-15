package minhdtm.example.movieapparchitecture.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.graphics.withTranslation
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.extension.isRtl
import kotlin.math.max
import kotlin.math.roundToInt

class HashtagCinemaDecoration(context: Context) : ItemDecoration() {

    private var drawable: Drawable?
    private var margin: Int

    private var decorBottom = 0

    init {
        val attrs = context.obtainStyledAttributes(
            R.style.Widget_MovieArc_HashtagCinemaDecoration,
            R.styleable.HashtagCinemaDecoration
        )

        drawable = attrs.getDrawable(R.styleable.HashtagCinemaDecoration_android_drawable)?.apply {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        }

        margin = attrs.getDimensionPixelOffset(R.styleable.HashtagCinemaDecoration_margin, 0)
        attrs.recycle()

        decorBottom = 2 * margin + (drawable?.intrinsicHeight ?: 0)
    }

    @Suppress("ReturnCount")
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (drawable == null) {
            return
        }

        val x = if (parent.isRtl()) {
            parent.paddingEnd + margin
        } else {
            parent.width - parent.paddingEnd - margin - drawable!!.intrinsicWidth
        }

        val yFromParentBottom = parent.height - parent.paddingBottom - margin - drawable!!.intrinsicHeight
        if (state.itemCount < 1) {
            // No children. Just draw at the bottom of the parrent
            drawDecoration(canvas, x, yFromParentBottom)
        }

        // Find the decorated view or bust.
        val child = findTargetChild(parent, state.itemCount - 1) ?: return
        val yFromChildBottom = child.bottom + child.translationY.roundToInt() + margin
        if (yFromChildBottom > parent.height) {
            return
        }

        val y = max(yFromChildBottom, yFromParentBottom)
        drawDecoration(canvas, x, y)
    }

    private fun drawDecoration(canvas: Canvas, x: Int, y: Int) {
        canvas.withTranslation(x = x.toFloat(), y = y.toFloat()) {
            drawable?.draw(canvas)
        }
    }

    private fun findTargetChild(parent: RecyclerView, adapterPosition: Int): View? {
        parent.forEach { child ->
            if (parent.getChildAdapterPosition(child) == adapterPosition) {
                return child
            }
        }

        return null
    }
}
