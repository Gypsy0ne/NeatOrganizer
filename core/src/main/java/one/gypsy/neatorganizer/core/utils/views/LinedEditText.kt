package one.gypsy.neatorganizer.core.utils.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import one.gypsy.neatorganizer.core.R

class LinedEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    private val mRect: Rect = Rect()
    private val mPaint: Paint = Paint()
    var lineColor: Int
        set(value) {
            mPaint.color = value
            invalidate()
        }
        get() = mPaint.color

    init {
        mPaint.style = Paint.Style.STROKE
        setAttributedLineColor(context, attrs)
    }

    private fun setAttributedLineColor(
        context: Context,
        attrs: AttributeSet?
    ) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LinedEditText,
            0, 0
        ).apply {
            try {
                mPaint.color = getInteger(R.styleable.LinedEditText_lineColor, DEFAULT_PAINT_COLOR)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        val count = lineCount
        val rect: Rect = mRect
        val paint: Paint = mPaint
        for (i in 0 until count) {
            val baseline = getLineBounds(i, rect)
            canvas.drawLine(
                rect.left.toFloat(),
                (baseline + 1).toFloat(),
                rect.right.toFloat(),
                (baseline + 1).toFloat(),
                paint
            )
        }
        super.onDraw(canvas)
    }

    companion object {
        private const val DEFAULT_PAINT_COLOR = -0x7fffff01
    }
}
