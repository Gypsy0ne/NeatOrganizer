package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.TaskWidgetsRepository
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class DeleteTaskWidget(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<Unit, DeleteTaskWidget.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(taskWidgetsRepository.delete(params.taskWidgetId))
            }
        } catch (exp: Exception) {
            Either.Left(
                DeleteTaskWidgetFailure(
                    exp
                )
            )
        }
    }

    data class Params(val taskWidgetId: Int)
    data class DeleteTaskWidgetFailure(val error: Exception) : Failure.FeatureFailure(error)
}