package one.gypsy.neatorganizer.domain.interactors.routines.reset

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshotDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.routines.reset.RoutineSnapshotsRepository

class AddRoutineSnapshot(
    private val routineSnapshotsRepository: RoutineSnapshotsRepository
) : BaseUseCase<Unit, AddRoutineSnapshot.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routineSnapshotsRepository.addRoutineSnapshot(params.snapshot))
            }
        } catch (exp: Exception) {
            Either.Left(
                AddRoutineSnapshotFailure(
                    exp
                )
            )
        }
    }

    data class Params(val snapshot: RoutineSnapshotDto)
    data class AddRoutineSnapshotFailure(val error: Exception) : Failure.FeatureFailure(error)
}
