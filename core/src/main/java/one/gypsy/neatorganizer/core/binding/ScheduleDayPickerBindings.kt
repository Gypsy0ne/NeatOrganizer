package one.gypsy.neatorganizer.core.binding

import androidx.databinding.BindingAdapter
import one.gypsy.neatorganizer.core.utils.views.ScheduleDayPicker
import one.gypsy.neatorganizer.core.utils.views.ScheduledDaysPickListener

@BindingAdapter("scheduleChangeListener")
fun setScheduleChangeListener(
    view: ScheduleDayPicker,
    scheduleChangeListener: ScheduledDaysPickListener
) {
    view.scheduledDaysChangeListener = scheduleChangeListener
}

@BindingAdapter("scheduleDaysStatus")
fun setScheduleDaysStatus(
    view: ScheduleDayPicker,
    scheduleDaysValues: List<Boolean>
) {
    view.scheduleDaysStatus = scheduleDaysValues
}
