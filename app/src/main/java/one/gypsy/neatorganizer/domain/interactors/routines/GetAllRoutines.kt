package one.gypsy.neatorganizer.domain.interactors.routines

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.routines.RoutinesRepository
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetAllRoutines(var dataSource: RoutinesRepository) :
    BaseUseCase<LiveData<List<Routine>>, Unit>() {
    override suspend fun run(params: Unit): Either<Failure, LiveData<List<Routine>>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(dataSource.getAllRoutinesObservable())
            }
        } catch (exp: Exception) {
            Either.Left(
                GetAllRoutinesFailure(
                    exp
                )
            )
        }
    }

    data class GetAllRoutinesFailure(val error: Exception) : Failure.FeatureFailure()
}