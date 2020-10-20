package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class UpdateTaskGroup(private val singleTaskGroupsRepository: SingleTaskGroupsRepository) :
    BaseUseCase<Unit, UpdateTaskGroup.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(singleTaskGroupsRepository.updateSingleTaskGroup(params.singleTaskGroupWithTasks))
            }
        } catch (exp: Exception) {
            Either.Left(
                UpdateSingleTaskGroupFailure(
                    exp
                )
            )
        }
    }

    data class Params(val singleTaskGroupWithTasks: SingleTaskGroupWithTasks)
    data class UpdateSingleTaskGroupFailure(val error: Exception) : Failure.FeatureFailure(error)
}