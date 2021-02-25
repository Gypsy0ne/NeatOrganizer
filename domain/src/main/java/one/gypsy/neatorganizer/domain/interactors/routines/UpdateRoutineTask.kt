package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntryDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.routines.RoutineTasksRepository

class UpdateRoutineTask(private val routineTasksRepository: RoutineTasksRepository) :
    BaseUseCase<Unit, UpdateRoutineTask.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routineTasksRepository.updateRoutineTask(params.routineTask))
            }
        } catch (exp: Exception) {
            Either.Left(
                UpdateRoutineTaskFailure(
                    exp
                )
            )
        }
    }

    data class Params(val routineTask: RoutineTaskEntryDto)
    data class UpdateRoutineTaskFailure(val error: Exception) : Failure.FeatureFailure(error)
}
