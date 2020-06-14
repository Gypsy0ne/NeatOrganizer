package one.gypsy.neatorganizer.binding

import androidx.databinding.BindingAdapter
import one.gypsy.neatorganizer.utils.views.ScheduleDayPicker
import one.gypsy.neatorganizer.utils.views.ScheduledDaysPickListener

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