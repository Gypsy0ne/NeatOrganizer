package one.gypsy.neatorganizer.binding

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("dateText")
fun setDateText(view: TextView, date: Date) {
    view.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
}

@BindingAdapter("strokeThrough")
fun setStrikeThrough(view: TextView, strokeThrough: Boolean) {
    if(strokeThrough) {
        view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
}