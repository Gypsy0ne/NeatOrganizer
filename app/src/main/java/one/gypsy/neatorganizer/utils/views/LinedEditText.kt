package one.gypsy.neatorganizer.utils.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class LinedEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    private val mRect: Rect = Rect()
    private val mPaint: Paint = Paint()

    init {
        mPaint.style = Paint.Style.STROKE
        mPaint.color = -0x7fffff01
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
}
