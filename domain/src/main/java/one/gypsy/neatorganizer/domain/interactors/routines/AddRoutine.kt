package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasksDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.routines.RoutinesRepository

class AddRoutine(private val routinesRepository: RoutinesRepository) :
    BaseUseCase<Long, AddRoutine.Params>() {

    override suspend fun run(params: Params): Either<Failure, Long> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routinesRepository.addRoutine(params.routine))
            }
        } catch (exp: Exception) {
            Either.Left(
                AddRoutineFailure(
                    exp
                )
            )
        }
    }

    data class Params(val routine: RoutineWithTasksDto)
    data class AddRoutineFailure(val error: Exception) : Failure.FeatureFailure(error)
}
