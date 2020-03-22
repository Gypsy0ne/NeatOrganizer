package one.gypsy.neatorganizer.utils

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class AxisDateFormatter: ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val date = Date(value.toLong())
        return SimpleDateFormat("dd/MM", Locale.ENGLISH).format(date)
    }
}

class AxisInteractionFormatter(private val availableValues: Array<String>) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return if(value <= 0 || value > availableValues.size)
            ""
        else
            availableValues[value.toInt() - 1]
    }
}