package minhdtm.example.movieapparchitecture.widget.snap

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import minhdtm.example.movieapparchitecture.extension.getSnapPosition

class SnapOnScrollListener(
    private val snapHelper: SnapHelper,
    private val behavior: Behavior = Behavior.NOTIFY_ON_SCROLL,
    private val onSnapPositionChanged: ((Int) -> Unit)? = null
) : RecyclerView.OnScrollListener() {

    enum class Behavior {
        NOTIFY_ON_SCROLL,
        NOTIFY_ON_SCROLL_STATE_IDLE
    }

    private var snapPosition = RecyclerView.NO_POSITION

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (behavior == Behavior.NOTIFY_ON_SCROLL) {
            maybeNotifySnapPositionChange(recyclerView)
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (behavior == Behavior.NOTIFY_ON_SCROLL_STATE_IDLE && newState == RecyclerView.SCROLL_STATE_IDLE) {
            maybeNotifySnapPositionChange(recyclerView)
        }
    }

    private fun maybeNotifySnapPositionChange(recyclerView: RecyclerView) {
        val snapPosition = snapHelper.getSnapPosition(recyclerView)
        val snapPositionChanged = this.snapPosition != snapPosition
        if (snapPositionChanged) {
            onSnapPositionChanged?.invoke(snapPosition)
            this.snapPosition = snapPosition
        }
    }
}
