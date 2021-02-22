package one.gypsy.neatorganizer.domain.interactors.routines

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasksDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.routines.RoutinesRepository

class GetAllRoutines(private val dataSource: RoutinesRepository) :
    BaseUseCase<LiveData<List<RoutineWithTasksDto>>, Unit>() {

    override suspend fun run(params: Unit): Either<Failure, LiveData<List<RoutineWithTasksDto>>> {
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
