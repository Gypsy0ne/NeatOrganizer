package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.routines.reset.RoutinesResetSnapshooter

class RunAllRoutinesSnapshotReset(private val routinesResetSnapshooter: RoutinesResetSnapshooter) :
    BaseUseCase<Unit, Unit>() {

    override suspend fun run(unit: Unit): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(routinesResetSnapshooter.performWeeklyRoutinesReset())
            }
        } catch (exp: Exception) {
            Either.Left(
                RunAllRoutinesSnapshotReset(
                    exp
                )
            )
        }
    }

    data class RunAllRoutinesSnapshotReset(val error: Exception) : Failure.FeatureFailure(error)
}
