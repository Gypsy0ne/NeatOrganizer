package one.gypsy.neatorganizer.domain.interactors

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.domain.datasource.UserSingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
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
            Either.Left(GetAllSingleTaskGroupsFailure(exp))
        }
    }

    data class GetAllSingleTaskGroupsFailure(val error: Exception): Failure.FeatureFailure()
}