package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntryDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.routines.RoutineTasksRepository

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

    data class Params(val routineTask: RoutineTaskEntryDto)
    data class RemoveRoutineTaskFailure(val error: Exception) : Failure.FeatureFailure(error)
}
