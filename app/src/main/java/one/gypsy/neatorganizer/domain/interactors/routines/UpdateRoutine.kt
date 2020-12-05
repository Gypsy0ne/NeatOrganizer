package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.routines.RoutinesRepository
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasks
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class UpdateRoutine(private val routinesRepository: RoutinesRepository) :
    BaseUseCase<Unit, UpdateRoutine.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routinesRepository.updateRoutine(params.routine))
            }
        } catch (exp: Exception) {
            Either.Left(
                UpdateRoutineFailure(
                    exp
                )
            )
        }
    }

    data class Params(val routine: RoutineWithTasks)
    data class UpdateRoutineFailure(val error: Exception) : Failure.FeatureFailure(error)
}
