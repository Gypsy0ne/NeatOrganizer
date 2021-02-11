package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.tasks.TaskWidgetsRepository

class GetAllTaskWidgetIds(private val taskWidgetsRepository: TaskWidgetsRepository) :
    BaseUseCase<IntArray, Unit>() {

    override suspend fun run(params: Unit): Either<Failure, IntArray> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(taskWidgetsRepository.getAllWidgetIds())
            }
        } catch (exp: Exception) {
            Either.Left(
                GetAllTaskWidgetIdsFailure(
                    exp
                )
            )
        }
    }

    data class GetAllTaskWidgetIdsFailure(val error: Exception) :
        Failure.FeatureFailure(error)
}
