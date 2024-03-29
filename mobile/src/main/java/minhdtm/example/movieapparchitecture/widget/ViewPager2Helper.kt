package minhdtm.example.movieapparchitecture.widget

import android.animation.ValueAnimator
import android.view.animation.AnimationUtils
import androidx.core.animation.addListener
import androidx.viewpager2.widget.ViewPager2

class ViewPager2Helper(private val viewPager: ViewPager2) {

    private val fastOutSlowIn =
        AnimationUtils.loadInterpolator(viewPager.context, android.R.interpolator.fast_out_slow_in)

    fun advance() {
        if (viewPager.width <= 0) return

        // Calculate the next page automatically.
        val current = viewPager.currentItem
        // With viewPager, instead of getItemCount(), override getCount().
        val next = ((current + 1) % requireNotNull(viewPager.adapter).itemCount)
        val pages = next - current
        val dragDistance = pages * viewPager.width

        // Setup Animation for ViewPager.
        ValueAnimator.ofInt(0, dragDistance).apply {
            var dragProgress = 0
            var draggedPages = 0

            addListener(
                onStart = { viewPager.beginFakeDrag() },
                onEnd = { viewPager.endFakeDrag() }
            )

            addUpdateListener {
                if (!viewPager.isFakeDragging) {
                    // Some time onAnimationUpdate is called with initial value before onAnimationStart is called.
                    return@addUpdateListener
                }

                val dragPoint = (animatedValue as Int)
                val dragBy = dragPoint - dragProgress
                viewPager.fakeDragBy(-dragBy.toFloat())
                dragProgress = dragPoint

                // Fake dragging doesn't let you drag more than one page width.
                // If we want to do this then need to end and start a new fake drag.
                val draggedPagesProgress = dragProgress / viewPager.width
                if (draggedPagesProgress != draggedPages) {
                    viewPager.endFakeDrag()
                    viewPager.beginFakeDrag()
                    draggedPages = draggedPagesProgress
                }

                duration = if (pages == 1) PAGE_CHANGE_DURATION else MULTI_PAGE_CHANGE_DURATION
                interpolator = fastOutSlowIn
            }
        }.start()
    }

    companion object {
        private const val PAGE_CHANGE_DURATION = 400L
        private const val MULTI_PAGE_CHANGE_DURATION = 600L
    }
}
