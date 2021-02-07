package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.repositories.tasks.TaskWidgetsRepository
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class DeleteTaskWidgetById(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<Unit, DeleteTaskWidgetById.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(taskWidgetsRepository.deleteTaskWidgetById(params.taskWidgetId))
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
