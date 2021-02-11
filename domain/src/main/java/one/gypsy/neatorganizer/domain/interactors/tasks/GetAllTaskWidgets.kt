package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.tasks.TaskWidgetsRepository

class GetAllTaskWidgets(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<LiveData<List<TaskWidgetEntry>>, Unit>() {

    override suspend fun run(params: Unit): Either<Failure, LiveData<List<TaskWidgetEntry>>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(taskWidgetsRepository.getAllTaskWidgets())
            }
        } catch (exp: Exception) {
            Either.Left(
                GetAllTaskWidgetsFailure(
                    exp
                )
            )
        }
    }

    data class GetAllTaskWidgetsFailure(val error: Exception) :
        Failure.FeatureFailure(error)
}
