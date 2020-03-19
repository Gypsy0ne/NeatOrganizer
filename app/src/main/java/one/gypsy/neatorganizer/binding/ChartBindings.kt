package one.gypsy.neatorganizer.binding

import androidx.databinding.BindingAdapter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import one.gypsy.neatorganizer.domain.dto.InteractionEntry
import one.gypsy.neatorganizer.utils.AxisDateFormatter

@BindingAdapter("interactionChartData")
fun bindInteractionLineChart(lineChart: LineChart, interactionData: List<InteractionEntry>) {
    lineChart.apply {
        this.setPinchZoom(false)
        this.setDrawGridBackground(false)
        this.setMaxVisibleValueCount(30)
    }
    lineChart.xAxis.apply {
        this.position = XAxis.XAxisPosition.BOTTOM
        this.labelCount = 2
        this.valueFormatter = AxisDateFormatter()
    }
}

fun formatInteractionEntriesToChartEntries(interactionEntries: List<InteractionEntry>) {
    val values = interactionEntries.map { it ->

    }
}