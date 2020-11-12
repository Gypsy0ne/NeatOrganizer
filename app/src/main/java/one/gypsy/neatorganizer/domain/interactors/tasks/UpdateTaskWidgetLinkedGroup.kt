package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.TaskWidgetsRepository
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class UpdateTaskWidgetLinkedGroup(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<Unit, UpdateTaskWidgetLinkedGroup.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(
                    taskWidgetsRepository.updateLinkedTaskGroup(
                        params.taskWidgetId,
                        params.taskGroupId
                    )
                )
            }
        } catch (exp: Exception) {
            Either.Left(
                UpdateTaskWidgetLinkedGroupFailure(
                    exp
                )
            )
        }
    }

    data class Params(val taskWidgetId: Int, val taskGroupId: Long)
    data class UpdateTaskWidgetLinkedGroupFailure(val error: Exception) :
        Failure.FeatureFailure(error)
}