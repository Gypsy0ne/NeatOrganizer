package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTaskGroupsRepository

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
