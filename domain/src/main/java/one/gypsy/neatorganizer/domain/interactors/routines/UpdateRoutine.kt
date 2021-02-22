package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasksDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.routines.RoutinesRepository

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

    data class Params(val routine: RoutineWithTasksDto)
    data class UpdateRoutineFailure(val error: Exception) : Failure.FeatureFailure(error)
}
