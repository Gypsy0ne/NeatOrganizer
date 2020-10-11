package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.TaskWidgetPreferencesRepository
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class LoadTaskWidget(private val taskWidgetPreferencesRepository: TaskWidgetPreferencesRepository) :
    BaseUseCase<TaskWidgetEntry, LoadTaskWidget.Params>() {

    override suspend fun run(params: Params): Either<Failure, TaskWidgetEntry> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(taskWidgetPreferencesRepository.load(params.taskWidgetId))
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