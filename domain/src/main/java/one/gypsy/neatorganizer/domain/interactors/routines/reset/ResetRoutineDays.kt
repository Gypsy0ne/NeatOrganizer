package one.gypsy.neatorganizer.domain.interactors.routines.reset

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.routines.RoutineTasksRepository
import java.util.Calendar.FRIDAY
import java.util.Calendar.MONDAY
import java.util.Calendar.SATURDAY
import java.util.Calendar.SUNDAY
import java.util.Calendar.THURSDAY
import java.util.Calendar.TUESDAY
import java.util.Calendar.WEDNESDAY

class ResetRoutineDays(
    private val routineTasksRepository: RoutineTasksRepository
) : BaseUseCase<Unit, ResetRoutineDays.Params>() {

    private val resetDaysActions = mapOf(
        MONDAY to routineTasksRepository::resetMondayTasks,
        TUESDAY to routineTasksRepository::resetTuesdayTasks,
        WEDNESDAY to routineTasksRepository::resetWednesdayTasks,
        THURSDAY to routineTasksRepository::resetThursdayTasks,
        FRIDAY to routineTasksRepository::resetFridayTasks,
        SATURDAY to routineTasksRepository::resetSaturdayTasks,
        SUNDAY to routineTasksRepository::resetSundayTasks
    )

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                params.days.mapNotNull { day ->
                    resetDaysActions[day.calendarDayOfWeek]?.let { resetFunction -> async { resetFunction() } }
                }.awaitAll()
                Either.Right(Unit)
            }
        } catch (exp: Exception) {
            Either.Left(
                AddRoutineFailure(
                    exp
                )
            )
        }
    }

    data class Params(val days: List<DayOfWeek>)
    data class AddRoutineFailure(val error: Exception) : Failure.FeatureFailure(error)
}
