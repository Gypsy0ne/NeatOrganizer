package one.gypsy.neatorganizer.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import one.gypsy.neatorganizer.R
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("dateText")
fun setDateText(view: TextView, date: Date) {
    view.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
}

@BindingAdapter("creationTimestamp")
fun setCreationDate(view: TextView, timestamp: Long) {
    view.text = view.context.getString(
        R.string.item_note_creation_date_prefix,
        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(timestamp)
    )
}
