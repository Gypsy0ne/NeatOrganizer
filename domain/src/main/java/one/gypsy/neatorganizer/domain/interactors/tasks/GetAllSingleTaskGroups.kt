package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasksDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTaskGroupsRepository

class GetAllSingleTaskGroups(private val dataSource: SingleTaskGroupsRepository) :
    BaseUseCase<LiveData<List<SingleTaskGroupWithTasksDto>>, Unit>() {

    override suspend fun run(params: Unit): Either<Failure, LiveData<List<SingleTaskGroupWithTasksDto>>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(dataSource.getAllSingleTaskGroups())
            }
        } catch (exp: Exception) {
            Either.Left(
                GetAllSingleTaskGroupsFailure(
                    exp
                )
            )
        }
    }

    data class GetAllSingleTaskGroupsFailure(val error: Exception) : Failure.FeatureFailure()
}
