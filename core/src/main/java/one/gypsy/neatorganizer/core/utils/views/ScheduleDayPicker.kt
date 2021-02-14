package one.gypsy.neatorganizer.core.utils.views

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.core.utils.extensions.getDimen

class ScheduleDayPicker(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val daysOfWeekIndicators: List<Button> = List(7) {
        createScheduleDayIndicator(context, it)
    }
    var scheduledDaysChangeListener: ScheduledDaysPickListener? = null
    var interactable: Boolean = true
        set(value) {
            daysOfWeekIndicators.forEach {
                it.isClickable = value
            }
            field = value
        }
    var scheduleDaysStatus: List<Boolean>
        get() = daysOfWeekIndicators.map { it.isSelected }
        set(value) {
            value.forEachIndexed { index, value ->
                daysOfWeekIndicators[index].isSelected = value
            }
        }

    init {
        setUpParentView()
    }

    private fun setUpParentView() {
        weightSum = 7f
        for (dayIndicator in daysOfWeekIndicators) {
            addView(dayIndicator)
        }
    }

    private fun createScheduleDayIndicator(context: Context, index: Int) = Button(context).apply {
        setUpIndicatorView(index)
    }

    private fun Button.setUpIndicatorView(
        index: Int
    ) = this.apply {
        this.setOnClickListener {
            onIndicatorClick(it)
        }
        setUpIndicatorViewLayout(index)
    }

    private fun onIndicatorClick(it: View) {
        it.isSelected = !it.isSelected
        scheduledDaysChangeListener?.onScheduleChange(
            daysOfWeekIndicators.map { it.isSelected }
        )
    }

    private fun Button.setUpIndicatorViewLayout(index: Int) {
        layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT
        ).apply {
            background = context.resources.getDrawable(
                R.drawable.bg_view_schedule_days_indicator,
                context.theme
            )
            setAutoSizeTextTypeUniformWithConfiguration(6, 24, 1, TypedValue.COMPLEX_UNIT_SP)
            weight = 1f
            setMargins(
                getDimen(R.dimen.normal_25),
                getDimen(R.dimen.normal_50),
                getDimen(R.dimen.normal_25),
                getDimen(R.dimen.normal_50)
            )
            text = context.resources.getStringArray(R.array.schedule_days_short)[index]
        }
    }
}
