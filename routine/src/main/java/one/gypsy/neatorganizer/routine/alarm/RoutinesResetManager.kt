package one.gypsy.neatorganizer.routine.alarm

import kotlinx.coroutines.CoroutineScope
import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshotDto
import one.gypsy.neatorganizer.domain.interactors.routines.reset.AddRoutineSnapshot
import one.gypsy.neatorganizer.domain.interactors.routines.reset.DayOfWeek
import one.gypsy.neatorganizer.domain.interactors.routines.reset.GetLastRoutineSnapshot
import one.gypsy.neatorganizer.domain.interactors.routines.reset.ResetRoutineDays
import java.util.Calendar
import java.util.Date
import kotlin.math.floor

class RoutinesResetManager(
    private val resetRoutineDaysUseCase: ResetRoutineDays,
    private val getLastSnapshotUseCase: GetLastRoutineSnapshot,
    private val addSnapshotUseCase: AddRoutineSnapshot
) {
    private lateinit var onComplete: () -> Unit

    fun resetRoutineTasks(coroutineScope: CoroutineScope, onCompleteListener: () -> Unit) {
        onComplete = onCompleteListener
        getLastSnapshotUseCase.invoke(coroutineScope, Unit) { result ->
            result.either(
                { onComplete() },
                { routineSnapshotDto ->
                    routineSnapshotDto?.let {
                        coroutineScope.resetTasksBasedOnLastReset(it)
                    } ?: coroutineScope.invokeAddSnapshot()
                }
            )
        }
    }

    private fun CoroutineScope.resetTasksBasedOnLastReset(routineSnapshot: RoutineSnapshotDto) =
        routineSnapshot.getDaysSinceLastReset()
            .takeIf { it.isNotEmpty() }
            ?.let { daysSinceLastReset ->
                resetRoutineDaysUseCase.invoke(
                    this,
                    ResetRoutineDays.Params(days = daysSinceLastReset)
                ) {
                    it.either({}, { invokeAddSnapshot() })
                }
            } ?: onComplete()

    private fun RoutineSnapshotDto.getDaysSinceLastReset(): List<DayOfWeek> {
        val todaysReset = getTodayResetTime()

        val noDaysBetweenLastResetAndNow = floor(
            (todaysReset.timeInMillis - this.resetDate.time + todaysReset.timeZone.rawOffset).toFloat() / MILLIS_IN_DAY
        ).toInt()

        return if (noDaysBetweenLastResetAndNow > 7) {
            List(7) { DayOfWeek(it) }
        } else {
            List(noDaysBetweenLastResetAndNow) {
                todaysReset.add(Calendar.DAY_OF_WEEK, -1)
                DayOfWeek(todaysReset[Calendar.DAY_OF_WEEK])
            }
        }
    }

    private fun getTodayResetTime() = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 99)
    }

    private fun CoroutineScope.invokeAddSnapshot() = addSnapshotUseCase.invoke(
        this,
        AddRoutineSnapshot.Params(createResetSnapshot())
    ) {
        it.either({}, { onComplete() })
    }

    private fun createResetSnapshot() = RoutineSnapshotDto(resetDate = Date())

    companion object {
        private const val MILLIS_IN_DAY = 86400000
    }
}
