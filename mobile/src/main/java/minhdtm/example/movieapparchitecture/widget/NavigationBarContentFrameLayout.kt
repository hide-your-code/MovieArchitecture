package minhdtm.example.movieapparchitecture.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.Window
import android.view.WindowInsets
import android.widget.FrameLayout
import kotlin.LazyThreadSafetyMode.NONE
import minhdtm.example.movieapparchitecture.R

/**
 * A [FrameLayout] which will draw a divider on the edge of the navigation bar. Similar in
 * functionality to what [Window.setNavigationBarDividerColor] provides, but works with translucent
 * navigation bar colors.
 */
class NavigationBarContentFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var navigationBarDividerColor: Int = 0
        set(value) {
            field = value
            dividerDrawable.color = value
        }

    var navigationBarDividerSize: Int = 0
        set(value) {
            field = value
            updateDividerBounds()
        }

    private var lastInserts: WindowInsets? = null

    private val dividerDrawable by lazy(NONE) {
        ColorDrawable(navigationBarDividerColor).apply {
            callback = this@NavigationBarContentFrameLayout
            alpha = DIVIDER_ALPHA
        }
    }

    init {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.NavigationBarContentFrameLayout,
            defStyleAttr,
            R.style.Widget_MovieArc_NavigationBarContentFrameLayout
        )

        navigationBarDividerColor = a.getColor(
            R.styleable.NavigationBarContentFrameLayout_navigationBarBorderColor,
            Color.MAGENTA
        )

        navigationBarDividerSize = a.getDimensionPixelSize(
            R.styleable.NavigationBarContentFrameLayout_navigationBarBorderSize,
            0
        )

        a.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateDividerBounds()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val d = dividerDrawable
        if (!d.bounds.isEmpty) {
            d.draw(canvas)
        }
    }

    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        super.onApplyWindowInsets(insets)
        updateDividerBounds()
        return insets
    }

    @Suppress("DEPRECATION")
    private fun updateDividerBounds() {
        val d = dividerDrawable
        val inserts = lastInserts

        val bottomInset: Int
        val leftInset: Int
        val rightInset: Int

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            leftInset = inserts?.systemWindowInsetLeft ?: 0
            rightInset = inserts?.systemWindowInsetRight ?: 0
            bottomInset = inserts?.systemWindowInsetBottom ?: 0
        } else {
            val systemWindow = inserts?.getInsets(
                WindowInsets.Type.systemBars() or WindowInsets.Type.ime()
            ) ?: return
            leftInset = systemWindow.left
            rightInset = systemWindow.right
            bottomInset = systemWindow.bottom
        }

        when {
            bottomInset > 0 -> {
                // Display divider above bottom inset
                d.setBounds(
                    left,
                    bottom - bottomInset,
                    right,
                    bottom + navigationBarDividerSize - bottomInset
                )
            }

            leftInset > 0 -> {
                // Display divider to the right of the left inset
                d.setBounds(
                    leftInset - navigationBarDividerSize,
                    top,
                    leftInset,
                    bottom
                )
            }

            rightInset > 0 -> {
                // Display divider to the left of the right inset
                d.setBounds(
                    right - rightInset,
                    top,
                    right + navigationBarDividerSize - rightInset,
                    bottom
                )
            }

            else -> {
                // Display divider to the left of the right inset
                d.setBounds(0, 0, 0, 0)
            }
        }

        // Update our will-not-draw flag
        setWillNotDraw(d.bounds.isEmpty)
    }

    companion object {
        private const val DIVIDER_ALPHA = 255
    }
}
