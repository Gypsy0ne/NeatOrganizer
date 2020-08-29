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

@BindingAdapter(value = ["removeItemsCount", "removeGroupTypeName"], requireAll = true)
fun setRemoveGroupHeaderText(view: TextView, subItemsCount: Int, groupTypeName: String) {
    view.text = view.context.resources.getQuantityString(
        R.plurals.subItemsCount,
        subItemsCount,
        subItemsCount,
        groupTypeName
    )
}
