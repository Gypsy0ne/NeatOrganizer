package one.gypsy.neatorganizer.domain.interactors.routines.reset

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshotDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.routines.reset.RoutineSnapshotsRepository

class GetLastRoutineSnapshot(
    private val routineSnapshotsRepository: RoutineSnapshotsRepository
) : BaseUseCase<RoutineSnapshotDto?, Unit>() {

    override suspend fun run(params: Unit): Either<Failure, RoutineSnapshotDto?> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routineSnapshotsRepository.getLastRoutineSnapshot())
            }
        } catch (exp: Exception) {
            Either.Left(
                GetLastRoutineSnapshotFailure(
                    exp
                )
            )
        }
    }

    data class GetLastRoutineSnapshotFailure(val error: Exception) : Failure.FeatureFailure(error)
}
