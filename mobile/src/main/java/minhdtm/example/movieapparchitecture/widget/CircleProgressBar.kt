package minhdtm.example.movieapparchitecture.widget

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.os.Build
import android.text.Layout
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.StaticLayout
import android.text.TextPaint
import android.text.style.RelativeSizeSpan
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.extension.spToPx
import kotlin.math.roundToInt

@SuppressWarnings("MagicNumber")
class CircleProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    /**
     * Start progress at 12 o'clock
     */
    private val startAngle = -90f

    /**
     * ProgressBar's line thickness
     */
    private var stokeWidth = 4f

    private var rectF: RectF = RectF()
    private var backgroundPaint: Paint
    private var foregroundPaint: Paint
    private var textPaint: TextPaint

    private var progress = 0f
    private var min: Int = 0
    private var max: Int = 100
    private var progressColor = Color.DKGRAY

    private var textVisibility = 0
    private var textColor = Color.DKGRAY
    private var textSize = 38.5f

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar, 0, 0)

        try {
            typedArray.run {
                stokeWidth = getDimension(R.styleable.CircleProgressBar_progress_bar_thickness, stokeWidth)
                progress = getFloat(R.styleable.CircleProgressBar_progress, progress)
                progressColor = getInt(R.styleable.CircleProgressBar_progress_bar_color, progressColor)
                min = getInt(R.styleable.CircleProgressBar_min, min)
                max = getInt(R.styleable.CircleProgressBar_max, max)
                textColor = getInt(R.styleable.CircleProgressBar_text_color, textColor)
                textSize = getDimension(R.styleable.CircleProgressBar_text_size, textSize)
                textVisibility = getInt(R.styleable.CircleProgressBar_text_visibility, textVisibility)
            }
        } finally {
            typedArray.recycle()
        }

        backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = adjustAlpha(this@CircleProgressBar.progressColor, ALPHA_COLOR_BACKGROUND)
            style = Paint.Style.STROKE
            strokeWidth = this@CircleProgressBar.stokeWidth
        }

        foregroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = this@CircleProgressBar.progressColor
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = this@CircleProgressBar.stokeWidth
        }

        textPaint = TextPaint().apply {
            color = this@CircleProgressBar.textColor
            textSize = this@CircleProgressBar.textSize
            typeface = Typeface.create("Google Sans", Typeface.BOLD)
        }
    }

    fun setStrokeWidth(value: Float) {
        this.stokeWidth = value
        backgroundPaint.strokeWidth = value
        foregroundPaint.strokeWidth = value
        invalidate()
        requestLayout()
    }

    fun getStrokeWidth(): Float = stokeWidth

    fun setProgress(value: Float) {
        this.progress = value
        invalidate()
    }

    fun getProgress(): Float = progress

    fun setMin(value: Int) {
        this.min = value
        invalidate()
    }

    fun getMin(): Int = min

    fun setMax(value: Int) {
        this.max = value
        invalidate()
    }

    fun getMax(): Int = max

    fun setColor(value: Int) {
        this.progressColor = value
        backgroundPaint.color = adjustAlpha(value, ALPHA_COLOR_BACKGROUND)
        foregroundPaint.color = value
        invalidate()
        requestLayout()
    }

    fun getColor(): Int = progressColor

    fun setTextVisibility(visibility: Int) {
        textVisibility = when (visibility) {
            GONE -> 0
            VISIBLE -> 1
            else -> 0
        }
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val minSize = height.coerceAtMost(width)
        setMeasuredDimension(minSize, minSize)
        rectF.set(0 + stokeWidth / 2, 0 + stokeWidth / 2, minSize - stokeWidth / 2, minSize - stokeWidth / 2)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawProgress(canvas)
        drawTextPercent(canvas)
    }

    private fun drawProgress(canvas: Canvas?) {
        canvas?.drawOval(rectF, backgroundPaint)
        val angel = DEGREES_OVAL * progress / max
        canvas?.drawArc(rectF, startAngle, angel, false, foregroundPaint)
    }

    private fun drawTextPercent(canvas: Canvas?) {
        val text = "${progress.toInt()}%"

        val str = SpannableStringBuilder(text).apply {
            setSpan(
                RelativeSizeSpan(SCALE_PERCENT),
                text.length - 1,
                text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        val staticLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(str, 0, text.length, textPaint, TEXT_PERCENT_WIDTH)
                .setAlignment(Layout.Alignment.ALIGN_CENTER)
                .setIncludePad(false)
                .build()
        } else {
            StaticLayout(
                str,
                textPaint,
                TEXT_PERCENT_WIDTH,
                Layout.Alignment.ALIGN_CENTER,
                1f,
                0f,
                false
            )
        }

        canvas?.let {
            val xPosition = it.width / 2f - (staticLayout.width / 2f)
            val yPosition = (it.height / 2f) - (staticLayout.height / 2f)
            it.save()
            it.translate(xPosition, yPosition)
            staticLayout.draw(it)
            it.restore()
        }
    }

    /**
     * Transparent the given color by the factor
     * The more the factor closer to zero the more the color gets transparent
     *
     * @param color The color to transparent.
     * @param factor 0.0f to 1.0f
     * @return Int - A transplanted color
     */
    private fun adjustAlpha(color: Int, factor: Float): Int {
        val alpha = (Color.alpha(color) * factor).roundToInt()
        val red = Color.red(color)
        val blue = Color.blue(color)
        val green = Color.green(color)

        return Color.argb(alpha, red, green, blue)
    }

    /**
     * Lighten the given color by the factor
     *
     * @param color The color to lighten
     * @param factor 0.0f to 1.0f
     * @return Int - A brighter color
     */
    fun lightenColor(color: Int, factor: Float): Int {
        val red = Color.red(color) * factor
        val green = Color.green(color) * factor
        val blue = Color.blue(color) * factor

        val iRed = 255.coerceAtMost(red.toInt())
        val iGreen = 255.coerceAtMost(green.toInt())
        val iBlue = 255.coerceAtMost(blue.toInt())

        val iAlpha = Color.alpha(color)
        return Color.argb(iAlpha, iRed, iGreen, iBlue)
    }

    @SuppressLint("ObjectAnimatorBinding")
    fun setProgressWithAnimation(progress: Float) {
        ObjectAnimator.ofFloat(this, "progress", progress).apply {
            duration = DURATION_ANIMATION
            interpolator = DecelerateInterpolator()
        }.start()
    }

    companion object {
        private const val ALPHA_COLOR_BACKGROUND = 0.3f
        private const val DEGREES_OVAL = 360
        private const val SCALE_PERCENT = 0.5f
        private const val TEXT_PERCENT_WIDTH = 200
        private const val DURATION_ANIMATION = 2000L
    }
}
