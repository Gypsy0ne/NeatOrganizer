package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.routines.RoutineSchedulesRepository
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

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

    data class Params(val routineSchedule: RoutineSchedule)
    data class UpdateRoutineScheduleFailure(val error: Exception) : Failure.FeatureFailure(error)
}