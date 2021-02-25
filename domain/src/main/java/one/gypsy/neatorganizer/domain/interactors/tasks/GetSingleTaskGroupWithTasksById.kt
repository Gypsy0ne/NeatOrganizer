package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasksDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupWithTasksById.Params
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTaskGroupsRepository

class GetSingleTaskGroupWithTasksById(private val dataSource: SingleTaskGroupsRepository) :
    BaseUseCase<LiveData<SingleTaskGroupWithTasksDto>, Params>() {

    override suspend fun run(params: Params): Either<Failure, LiveData<SingleTaskGroupWithTasksDto>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(dataSource.getSingleTaskGroupWithTasksById(params.taskGroupId))
            }
        } catch (exp: Exception) {
            Either.Left(
                GetSingleTaskGroupWithTasksByIdFailure(
                    exp
                )
            )
        }
    }

    data class Params(val taskGroupId: Long)
    data class GetSingleTaskGroupWithTasksByIdFailure(val error: Exception) :
        Failure.FeatureFailure()
}
