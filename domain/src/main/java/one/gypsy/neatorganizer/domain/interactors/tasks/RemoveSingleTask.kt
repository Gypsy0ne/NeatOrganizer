package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTasksRepository

class RemoveSingleTask(private val singleTasksRepository: SingleTasksRepository) :
    BaseUseCase<Unit, RemoveSingleTask.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(singleTasksRepository.removeSingleTask(params.singleTask))
            }
        } catch (exp: Exception) {
            Either.Left(
                RemoveSingleTaskFailure(
                    exp
                )
            )
        }
    }

    data class Params(val singleTask: SingleTaskEntry)
    data class RemoveSingleTaskFailure(val error: Exception) : Failure.FeatureFailure(error)
}
