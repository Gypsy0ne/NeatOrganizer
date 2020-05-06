package one.gypsy.neatorganizer.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.utils.extensions.getDimen

@BindingAdapter("scheduleChangeListener")
fun setScheduleChangeListener(
    view: ScheduleDayPicker,
    scheduleChangeListener: ScheduleDayPicker.ScheduledDaysPickListener
) {
    view.scheduledDaysChangeListener = scheduleChangeListener
}

//TODO extract dimens
class ScheduleDayPicker(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val daysOfWeekIndicators: List<Button> = List(7) {
        createScheduleDayIndicator(context, it)
    }

    var scheduledDaysChangeListener: ScheduledDaysPickListener? = null

    interface ScheduledDaysPickListener {
        fun onScheduleChange(scheduledDays: List<Boolean>)
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
            weight = 1f
            setMargins(
                getDimen(R.dimen.margin_start_schedule_days),
                getDimen(R.dimen.margin_top_schedule_days),
                getDimen(R.dimen.margin_end_schedule_days),
                getDimen(R.dimen.margin_bottom_schedule_days)
            )
            text = context.resources.getStringArray(R.array.schedule_days_short)[index]
        }
    }
}