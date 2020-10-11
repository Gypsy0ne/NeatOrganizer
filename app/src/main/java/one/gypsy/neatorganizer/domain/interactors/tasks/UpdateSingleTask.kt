package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTasksRepository
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class UpdateSingleTask(private val singleTaskRepository: SingleTasksRepository) :
    BaseUseCase<Unit, UpdateSingleTask.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(singleTaskRepository.updateSingleTask(params.singleTask))
            }
        } catch (exp: Exception) {
            Either.Left(
                UpdateSingleTaskFailure(
                    exp
                )
            )
        }
    }

    data class Params(val singleTask: SingleTaskEntry)
    data class UpdateSingleTaskFailure(val error: Exception) : Failure.FeatureFailure(error)
}