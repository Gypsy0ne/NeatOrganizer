package one.gypsy.neatorganizer.binding

import android.graphics.Color
import androidx.databinding.BindingAdapter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.domain.dto.people.InteractionEntry
import one.gypsy.neatorganizer.utils.AxisDateFormatter
import one.gypsy.neatorganizer.utils.AxisInteractionFormatter

@BindingAdapter("interactionChartData")
fun bindInteractionLineChart(lineChart: LineChart, interactionData: List<InteractionEntry>?) {
    configureChart(lineChart)
    configureChartXAxis(lineChart)
    configureChartYAxis(lineChart)
    if (interactionData != null && interactionData.isNotEmpty()) {
        val data = LineData(createDataSet(formatInteractionEntriesToChartEntries(interactionData)))
        lineChart.data = data.apply {
//            setValueTextColor(Color.WHITE)
            setValueTextSize(9f)
        }
        lineChart.invalidate()
    }
}

fun formatInteractionEntriesToChartEntries(interactionEntries: List<InteractionEntry>) =
    interactionEntries.map {
        Entry(it.interactionDate.time.toFloat(), it.rating.toFloat())
    }


fun createDataSet(data: List<Entry>) = LineDataSet(data, "DataSet 1").apply {
    axisDependency = AxisDependency.LEFT
    color = ColorTemplate.getHoloBlue()
    valueTextColor = ColorTemplate.getHoloBlue()
    lineWidth = 1.5f
    setDrawCircles(true)
    setDrawValues(false)
    fillAlpha = 65
    fillColor = ColorTemplate.getHoloBlue()
    highLightColor = Color.rgb(244, 117, 117)
    setDrawCircleHole(false)
}

fun configureChartYAxis(lineChart: LineChart) {
    lineChart.axisLeft.apply {
        val availableValues = lineChart.context.resources.getStringArray(R.array.interaction_rating_levels)
        setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
//        textColor = ColorTemplate.getHoloBlue()
//        setDrawGridLines(true)
        isGranularityEnabled = true
        axisMinimum = 0.5f
        granularity = 1f

        yOffset = -9f
        axisMaximum = availableValues.size.toFloat() + 0.5f
        valueFormatter = AxisInteractionFormatter(availableValues)
    }
}

fun configureChartXAxis(lineChart: LineChart) {
    lineChart.xAxis.apply {
        position = XAxis.XAxisPosition.TOP_INSIDE
//            textSize = 10f
//            textColor = Color.rgb(255, 192, 56)
//            textColor = Color.WHITE
//            setDrawAxisLine(false)
//            setDrawGridLines(true)
//        isGranularityEnabled = true
//            granularity = 24f

        valueFormatter = AxisDateFormatter()
    }
}

fun configureChart(lineChart: LineChart) {
    lineChart.apply {
        setDrawGridBackground(false)
        axisRight.isEnabled = false
//        setViewPortOffsets(20f, 20f, 20f, 20f)
isScaleYEnabled = false
        setScaleMinima(-12f, 0f)
        description.isEnabled = false
        legend.isEnabled = false

    }
}