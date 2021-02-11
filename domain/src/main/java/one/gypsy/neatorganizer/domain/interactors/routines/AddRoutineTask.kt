package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.routines.RoutineTasksRepository

class AddRoutineTask(private val routineTasksRepository: RoutineTasksRepository) :
    BaseUseCase<Unit, AddRoutineTask.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routineTasksRepository.addRoutineTask(params.routineTask))
            }
        } catch (exp: Exception) {
            Either.Left(
                AddRoutineTaskFailure(
                    exp
                )
            )
        }
    }

    data class Params(val routineTask: RoutineTaskEntry)
    data class AddRoutineTaskFailure(val error: Exception) : Failure.FeatureFailure(error)
}
