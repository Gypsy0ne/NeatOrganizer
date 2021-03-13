package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.tasks.TaskWidgetsRepository

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
