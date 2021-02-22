package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.routines.RoutineScheduleDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.routines.RoutineSchedulesRepository

class UpdateRoutineSchedule(private val routineSchedulesRepository: RoutineSchedulesRepository) :
    BaseUseCase<Unit, UpdateRoutineSchedule.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routineSchedulesRepository.updateRoutineSchedule(params.routineSchedule))
            }
        } catch (exp: Exception) {
            Either.Left(
                UpdateRoutineScheduleFailure(
                    exp
                )
            )
        }
    }

    data class Params(val routineSchedule: RoutineScheduleDto)
    data class UpdateRoutineScheduleFailure(val error: Exception) : Failure.FeatureFailure(error)
}
