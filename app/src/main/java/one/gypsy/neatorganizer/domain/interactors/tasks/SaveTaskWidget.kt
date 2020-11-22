package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.TaskWidgetsRepository
import one.gypsy.neatorganizer.domain.dto.tasks.TitledTaskWidgetEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class SaveTaskWidget(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<Unit, SaveTaskWidget.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(taskWidgetsRepository.create(params.titledTaskWidgetEntry))
            }
        } catch (exp: Exception) {
            Either.Left(
                CreateTaskWidgetFailure(
                    exp
                )
            )
        }
    }

    data class Params(val titledTaskWidgetEntry: TitledTaskWidgetEntry)
    data class CreateTaskWidgetFailure(val error: Exception) : Failure.FeatureFailure(error)
}