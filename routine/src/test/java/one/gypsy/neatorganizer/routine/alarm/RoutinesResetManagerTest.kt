package one.gypsy.neatorganizer.routine.alarm

import org.junit.Test
import java.util.Calendar
import kotlin.math.floor

class RoutinesResetManagerTest {

    @Test
    fun `sialala`() {
        // 20.4 8:30
        // tworzenie date w ten sposb nie uwzglednia stref czasowych
        val lastReset = Calendar.getInstance().apply { timeInMillis = 1618898400000 }
        // 20.4 00:00
        val lastReset2 = Calendar.getInstance().apply { timeInMillis = 1618869600000 }
        // dzis 20:30
        val lastReset3 = Calendar.getInstance().apply { timeInMillis = 1619461859273 }

        val todaysReset = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val noDaysBetweenLastResetAndNow =
            floor((todaysReset.timeInMillis - lastReset3.timeInMillis) / 86400000.toFloat()).toInt()

        val daysToReset = hashSetOf<Int>()

        for (i in 1..noDaysBetweenLastResetAndNow) {
            todaysReset.add(Calendar.DAY_OF_WEEK, -1)
            daysToReset.add(todaysReset[Calendar.DAY_OF_WEEK])
        }
        println()
    }

//    @Test
//    fun `should
}
