package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTaskGroup.Params
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTaskGroupsRepository

class UpdateSingleTaskGroup(private val singleTaskGroupsRepository: SingleTaskGroupsRepository) :
    BaseUseCase<Unit, Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(singleTaskGroupsRepository.updateSingleTaskGroup(params.singleTaskGroup))
            }
        } catch (exp: Exception) {
            Either.Left(
                UpdateSingleTaskGroupFailure(
                    exp
                )
            )
        }
    }

    data class Params(val singleTaskGroup: SingleTaskGroup)
    data class UpdateSingleTaskGroupFailure(val error: Exception) : Failure.FeatureFailure(error)
}
