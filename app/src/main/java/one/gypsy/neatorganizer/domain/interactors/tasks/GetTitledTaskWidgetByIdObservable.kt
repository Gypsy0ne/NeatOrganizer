package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.TaskWidgetsRepository
import one.gypsy.neatorganizer.domain.dto.tasks.TitledTaskWidgetEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetTitledTaskWidgetByIdObservable(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<LiveData<TitledTaskWidgetEntry>, GetTitledTaskWidgetByIdObservable.Params>() {

    override suspend fun run(params: Params): Either<Failure, LiveData<TitledTaskWidgetEntry>> {
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