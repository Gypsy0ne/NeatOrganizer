package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.TitledTaskWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.LoadTitledTaskWidget.Params
import one.gypsy.neatorganizer.repositories.tasks.TaskWidgetsRepository
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class LoadTitledTaskWidget(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<TitledTaskWidgetEntry, Params>() {

    override suspend fun run(params: Params): Either<Failure, TitledTaskWidgetEntry> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(taskWidgetsRepository.getTitledTaskWidgetById(params.taskWidgetId))
            }
        } catch (exp: Exception) {
            Either.Left(
                LoadTitledTaskWidgetFailure(
                    exp
                )
            )
        }
    }

    data class Params(val taskWidgetId: Int)
    data class LoadTitledTaskWidgetFailure(val error: Exception) : Failure.FeatureFailure(error)
}
