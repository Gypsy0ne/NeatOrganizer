package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupWithTasksById.Params
import one.gypsy.neatorganizer.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetSingleTaskGroupWithTasksById(private val dataSource: SingleTaskGroupsRepository) :
    BaseUseCase<LiveData<SingleTaskGroupWithTasks>, Params>() {

    override suspend fun run(params: Params): Either<Failure, LiveData<SingleTaskGroupWithTasks>> {
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
