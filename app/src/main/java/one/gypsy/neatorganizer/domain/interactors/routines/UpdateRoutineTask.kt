package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class UpdateRoutineTask(var routineTasksRepository: RoutineTasksRepository) :
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

    data class Params(val routineTask: RoutineTaskEntry)
    data class UpdateRoutineTaskFailure(val error: Exception) : Failure.FeatureFailure(error)
}