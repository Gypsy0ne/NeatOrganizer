package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTaskGroupsRepository

class AddTaskGroup(private val singleTaskGroupsRepository: SingleTaskGroupsRepository) :
    BaseUseCase<Unit, AddTaskGroup.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
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
    data class AddSingleTaskGroupFailure(val error: Exception) : Failure.FeatureFailure(error)
}
