package one.gypsy.neatorganizer.binding

import android.widget.DatePicker
import androidx.databinding.BindingAdapter
import com.mikhaellopez.lazydatepicker.LazyDatePicker


@BindingAdapter("android:birthYear", "android:birthMonth", "android:birthDay", "android:onDateChanged")
fun setDate(view: DatePicker, year: Int, month: Int, day: Int, listener: DatePicker.OnDateChangedListener) {
    view.init(year, month, day, listener)
}

@BindingAdapter("android:birthYear", "android:birthMonth", "android:birthDay")
fun setDate(view: DatePicker, year: Int, month: Int, day: Int) {
    view.updateDate(year, month, day)
}

@BindingAdapter("dateChangeListener")
fun setDatePickListener(view: LazyDatePicker, onDatePickListener: LazyDatePicker.OnDatePickListener) {
    view.setOnDatePickListener(onDatePickListener)
}
