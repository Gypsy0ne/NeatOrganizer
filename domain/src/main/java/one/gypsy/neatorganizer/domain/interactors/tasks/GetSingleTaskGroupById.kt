package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTaskGroupsRepository

class GetSingleTaskGroupById(private val dataSource: SingleTaskGroupsRepository) :
    BaseUseCase<LiveData<SingleTaskGroupDto>, GetSingleTaskGroupById.Params>() {

    override suspend fun run(params: Params): Either<Failure, LiveData<SingleTaskGroupDto>> {
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
