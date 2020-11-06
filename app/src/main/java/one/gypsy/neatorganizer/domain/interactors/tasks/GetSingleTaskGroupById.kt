package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetSingleTaskGroupById(private val dataSource: SingleTaskGroupsRepository) :
    BaseUseCase<LiveData<SingleTaskGroup>, GetSingleTaskGroupById.Params>() {

    override suspend fun run(params: Params): Either<Failure, LiveData<SingleTaskGroup>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(dataSource.getSingleTaskGroupById(params.taskGroupId))
            }
        } catch (exp: Exception) {
            Either.Left(
                GetSingleTaskGroupByIdFailure(
                    exp
                )
            )
        }
    }

    data class Params(val taskGroupId: Long)
    data class GetSingleTaskGroupByIdFailure(val error: Exception) : Failure.FeatureFailure()
}