package one.gypsy.neatorganizer.utils

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat

class AxisDateFormatter: ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return dateFormat.format(value)    }
}