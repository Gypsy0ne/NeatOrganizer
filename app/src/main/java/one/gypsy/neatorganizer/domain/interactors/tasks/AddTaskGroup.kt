package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class AddTaskGroup(private val singleTaskGroupsRepository: SingleTaskGroupsRepository) :
    BaseUseCase<Long, AddTaskGroup.Params>() {

    override suspend fun run(params: Params): Either<Failure, Long> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(singleTaskGroupsRepository.addSingleTaskGroup(params.singleTaskGroup))
            }
        } catch (exp: Exception) {
            Either.Left(
                AddSingleTaskGroupFailure(
                    exp
                )
            )
        }
    }

    data class Params(val singleTaskGroup: SingleTaskGroup)
    data class AddSingleTaskGroupFailure(val error: Exception): Failure.FeatureFailure(error)
}