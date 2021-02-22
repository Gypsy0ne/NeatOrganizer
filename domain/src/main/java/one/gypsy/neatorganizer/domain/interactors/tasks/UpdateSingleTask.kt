package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntryDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTasksRepository

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

    data class Params(val singleTask: SingleTaskEntryDto)
    data class UpdateSingleTaskFailure(val error: Exception) : Failure.FeatureFailure(error)
}
