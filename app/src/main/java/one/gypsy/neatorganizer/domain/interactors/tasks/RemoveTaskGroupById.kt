package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class RemoveTaskGroupById(private val singleTaskGroupsRepository: SingleTaskGroupsRepository) :
    BaseUseCase<Unit, RemoveTaskGroupById.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(singleTaskGroupsRepository.removeSingleTaskGroupById(params.id))
            }
        } catch (exp: Exception) {
            Either.Left(
                RemoveRoutineFailure(
                    exp
                )
            )
        }
    }

    data class Params(val id: Long)
    data class RemoveRoutineFailure(val error: Exception) : Failure.FeatureFailure(error)
}