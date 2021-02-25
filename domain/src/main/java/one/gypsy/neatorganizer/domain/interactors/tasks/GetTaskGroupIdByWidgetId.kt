package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.tasks.GetTaskGroupIdByWidgetId.Params
import one.gypsy.neatorganizer.domain.repositories.tasks.TaskWidgetsRepository

class GetTaskGroupIdByWidgetId(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<Long, Params>() {

    override suspend fun run(params: Params): Either<Failure, Long> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(taskWidgetsRepository.getTaskGroupIdByWidgetId(params.taskWidgetId))
            }
        } catch (exp: Exception) {
            Either.Left(
                GetTaskGroupIdByWidgetIdFailure(
                    exp
                )
            )
        }
    }

    data class Params(val taskWidgetId: Int)
    data class GetTaskGroupIdByWidgetIdFailure(val error: Exception) : Failure.FeatureFailure(error)
}
