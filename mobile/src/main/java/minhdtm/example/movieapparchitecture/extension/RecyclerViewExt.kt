package minhdtm.example.movieapparchitecture.extension

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import minhdtm.example.movieapparchitecture.util.helper.snap.SnapOnScrollListener

fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager
    val snapView = findSnapView(layoutManager)
    return if (layoutManager == null || snapView == null) {
        RecyclerView.NO_POSITION
    } else {
        layoutManager.getPosition(snapView)
    }
}

fun RecyclerView.attachSnapHelperWithListener(
    snapHelper: SnapHelper,
    behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
    onSnapPositionChanged: ((Int) -> Unit)? = null
) {
    snapHelper.attachToRecyclerView(this)
    val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior, onSnapPositionChanged)
    addOnScrollListener(snapOnScrollListener)
}
