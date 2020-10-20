package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class RemoveTaskGroup(private val singleTaskGroupsRepository: SingleTaskGroupsRepository) :
    BaseUseCase<Unit, RemoveTaskGroup.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(singleTaskGroupsRepository.removeSingleTaskGroup(params.singleTaskGroupWithTasks))
            }
        } catch (exp: Exception) {
            Either.Left(
                RemoveSingleTaskGroupFailure(
                    exp
                )
            )
        }
    }

    data class Params(val singleTaskGroupWithTasks: SingleTaskGroupWithTasks)
    data class RemoveSingleTaskGroupFailure(val error: Exception) : Failure.FeatureFailure(error)
}