package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.TitledTaskWidgetEntryDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.tasks.GetTitledTaskWidgetByIdObservable.Params
import one.gypsy.neatorganizer.domain.repositories.tasks.TaskWidgetsRepository

class GetTitledTaskWidgetByIdObservable(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<LiveData<TitledTaskWidgetEntryDto>, Params>() {

    override suspend fun run(params: Params): Either<Failure, LiveData<TitledTaskWidgetEntryDto>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(taskWidgetsRepository.getTitledTaskWidgetByIdObservable(params.taskWidgetId))
            }
        } catch (exp: Exception) {
            Either.Left(
                GetTaskWidgetByIdObservableFailure(
                    exp
                )
            )
        }
    }

    data class Params(val taskWidgetId: Int)
    data class GetTaskWidgetByIdObservableFailure(val error: Exception) :
        Failure.FeatureFailure(error)
}
