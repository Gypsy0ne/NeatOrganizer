package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class ResetAllRoutineTasks @Inject constructor(var routineTasksRepository: RoutineTasksRepository) :
    BaseUseCase<Unit, Unit>() {

    override suspend fun run(unit: Unit): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routineTasksRepository.resetAllRoutineTasks())
            }
        } catch (exp: Exception) {
            Either.Left(
                ResetAllRoutineTasksFailure(
                    exp
                )
            )
        }
    }

    data class ResetAllRoutineTasksFailure(val error: Exception) : Failure.FeatureFailure(error)
}