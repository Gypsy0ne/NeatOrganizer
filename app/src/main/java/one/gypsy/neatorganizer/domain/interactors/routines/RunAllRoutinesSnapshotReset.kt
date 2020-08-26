package one.gypsy.neatorganizer.domain.interactors.routines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.interactors.routines.reset.RoutinesResetSnapshooter
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

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