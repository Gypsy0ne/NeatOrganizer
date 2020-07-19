package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.routines.RoutinesRepository
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class AddRoutine(var routinesRepository: RoutinesRepository) :
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

    data class Params(val routine: Routine)
    data class AddRoutineFailure(val error: Exception) : Failure.FeatureFailure(error)
}