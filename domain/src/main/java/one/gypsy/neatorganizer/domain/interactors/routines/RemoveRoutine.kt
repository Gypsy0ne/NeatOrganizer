package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasks
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.routines.RoutinesRepository

class RemoveRoutine(private val routinesRepository: RoutinesRepository) :
    BaseUseCase<Unit, RemoveRoutine.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routinesRepository.removeRoutine(params.routine))
            }
        } catch (exp: Exception) {
            Either.Left(
                RemoveRoutineFailure(
                    exp
                )
            )
        }
    }

    data class Params(val routine: RoutineWithTasks)
    data class RemoveRoutineFailure(val error: Exception) : Failure.FeatureFailure(error)
}
