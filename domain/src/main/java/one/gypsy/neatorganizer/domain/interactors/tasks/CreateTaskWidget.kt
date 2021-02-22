package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntryDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.tasks.CreateTaskWidget.Params
import one.gypsy.neatorganizer.domain.repositories.tasks.TaskWidgetsRepository

class CreateTaskWidget(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<Unit, Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(taskWidgetsRepository.createTaskWidget(params.taskWidgetEntry))
            }
        } catch (exp: Exception) {
            Either.Left(
                CreateTaskWidgetFailure(
                    exp
                )
            )
        }
    }

    data class Params(val taskWidgetEntry: TaskWidgetEntryDto)
    data class CreateTaskWidgetFailure(val error: Exception) : Failure.FeatureFailure(error)
}
