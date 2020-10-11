package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class RemoveRoutineTask(private val routineTasksRepository: RoutineTasksRepository) :
    BaseUseCase<Unit, RemoveRoutineTask.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routineTasksRepository.removeRoutineTask(params.routineTask))
            }
        } catch (exp: Exception) {
            Either.Left(
                RemoveRoutineTaskFailure(
                    exp
                )
            )
        }
    }

    data class Params(val routineTask: RoutineTaskEntry)
    data class RemoveRoutineTaskFailure(val error: Exception) : Failure.FeatureFailure(error)
}