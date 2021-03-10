package minhdtm.example.movieapparchitecture.widget.snap

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class CenterZoomLayoutManagerHelper(
    context: Context,
    orientation: Int,
    reverseLayout: Boolean
) : LinearLayoutManager(context, orientation, reverseLayout) {

    private val midPoint: Float
        get() = if (orientation == HORIZONTAL) {
            width / 2f
        } else {
            height / 2f
        }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        scaleChild()
        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        scaleChild()
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleChild()
    }

    private fun scaleChild() {
        val d0 = 0f
        val d1 = SHRINK_DISTANCE * midPoint
        val s0 = 1f
        val s1 = 1f - SHRINK_AMOUNT
        for (i in 0 until childCount) {
            val child = getChildAt(i)!!
            val d = d1.coerceAtMost(abs(midPoint - getChildMidPoint(child)))
            val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
            child.scaleX = scale
            child.scaleY = scale
        }
    }

    private fun getChildMidPoint(child: View): Float = if (orientation == HORIZONTAL) {
        (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
    } else {
        (getDecoratedBottom(child) + getDecoratedTop(child)) / 2f
    }

    companion object {
        private const val SHRINK_AMOUNT = 0.15f
        private const val SHRINK_DISTANCE = 0.9f
    }
}
