package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasksDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveTaskGroup.Params
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTaskGroupsRepository

class RemoveTaskGroup(private val singleTaskGroupsRepository: SingleTaskGroupsRepository) :
    BaseUseCase<Unit, Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(singleTaskGroupsRepository.removeSingleTaskGroupWithTasks(params.singleTaskGroupWithTasks))
            }
        } catch (exp: Exception) {
            Either.Left(
                RemoveTaskGroupFailure(
                    exp
                )
            )
        }
    }

    data class Params(val singleTaskGroupWithTasks: SingleTaskGroupWithTasksDto)
    data class RemoveTaskGroupFailure(val error: Exception) : Failure.FeatureFailure(error)
}
