package one.gypsy.neatorganizer.domain.interactors.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTasksByGroupId.Params
import one.gypsy.neatorganizer.repositories.tasks.SingleTasksRepository
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetAllSingleTasksByGroupId(private val dataSource: SingleTasksRepository) :
    BaseUseCase<List<SingleTaskEntry>, Params>() {

    override suspend fun run(params: Params): Either<Failure, List<SingleTaskEntry>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(dataSource.getAllSingleTasksByGroupId(params.taskGroupId))
            }
        } catch (exp: Exception) {
            Either.Left(
                GetAllSingleTasksByGroupIdFailure(
                    exp
                )
            )
        }
    }

    data class Params(val taskGroupId: Long)
    data class GetAllSingleTasksByGroupIdFailure(val error: Exception) : Failure.FeatureFailure()
}
