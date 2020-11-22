package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class AddRoutineTask(private val routineTasksRepository: RoutineTasksRepository) :
    BaseUseCase<Long, AddRoutineTask.Params>() {

    override suspend fun run(params: Params): Either<Failure, Long> {
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