package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class GetAllGroupsWithSingleTasks @Inject constructor(var dataSource: SingleTaskGroupsRepository): BaseUseCase<LiveData<List<SingleTaskGroup>>, Unit>() {
    override suspend fun run(params: Unit): Either<Failure, LiveData<List<SingleTaskGroup>>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(dataSource.getAllSingleTaskGroups())
            }
        } catch (exp: Exception) {
            Either.Left(
                GetAllSingleTaskGroupsFailure(
                    exp
                )
            )
        }
    }

    data class GetAllSingleTaskGroupsFailure(val error: Exception): Failure.FeatureFailure()
}