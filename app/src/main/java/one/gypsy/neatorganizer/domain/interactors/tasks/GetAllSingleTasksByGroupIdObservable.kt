package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTasksRepository
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTasksByGroupIdObservable.Params
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetAllSingleTasksByGroupIdObservable(private val dataSource: SingleTasksRepository) :
    BaseUseCase<LiveData<List<SingleTaskEntry>>, Params>() {

    override suspend fun run(params: Params): Either<Failure, LiveData<List<SingleTaskEntry>>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(dataSource.getAllSingleTasksByGroupIdObservable(params.taskGroupId))
            }
        } catch (exp: Exception) {
            Either.Left(
                GetAllSingleTasksByGroupIdObservableFailure(
                    exp
                )
            )
        }
    }

    data class Params(val taskGroupId: Long)
    data class GetAllSingleTasksByGroupIdObservableFailure(val error: Exception) :
        Failure.FeatureFailure()
}