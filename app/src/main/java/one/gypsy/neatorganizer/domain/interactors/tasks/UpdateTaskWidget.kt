package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.TaskWidgetsRepository
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class UpdateTaskWidget(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<TaskWidgetEntry, LoadTaskWidget.Params>() {

    override suspend fun run(params: Params): Either<Failure, TaskWidgetEntry> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(taskWidgetsRepository.load(params.taskWidgetId))
            }
        } catch (exp: Exception) {
            Either.Left(
                LoadTaskWidgetFailure(
                    exp
                )
            )
        }
    }

    data class Params(val taskWidgetId: Int)
    data class LoadTaskWidgetFailure(val error: Exception) : Failure.FeatureFailure(error)
}