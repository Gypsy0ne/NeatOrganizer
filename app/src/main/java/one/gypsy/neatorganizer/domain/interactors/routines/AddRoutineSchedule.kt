package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.routines.RoutineSchedulesRepository
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class AddRoutineSchedule @Inject constructor(var routineSchedulesRepository: RoutineSchedulesRepository) :
    BaseUseCase<Long, AddRoutineSchedule.Params>() {

    override suspend fun run(params: Params): Either<Failure, Long> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routineSchedulesRepository.addRoutineSchedule(params.routineSchedule))
            }
        } catch (exp: Exception) {
            Either.Left(
                AddRoutineScheduleFailure(
                    exp
                )
            )
        }
    }

    data class Params(val routineSchedule: RoutineSchedule)
    data class AddRoutineScheduleFailure(val error: Exception) : Failure.FeatureFailure(error)
}