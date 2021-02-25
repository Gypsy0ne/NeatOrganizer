package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.TitledTaskWidgetEntryDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.tasks.LoadTitledTaskWidget.Params
import one.gypsy.neatorganizer.domain.repositories.tasks.TaskWidgetsRepository

class LoadTitledTaskWidget(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<TitledTaskWidgetEntryDto, Params>() {

    override suspend fun run(params: Params): Either<Failure, TitledTaskWidgetEntryDto> {
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
