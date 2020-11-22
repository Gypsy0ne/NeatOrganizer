package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.TaskWidgetsRepository
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

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