package one.gypsy.neatorganizer.domain.interactors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.data.repositories.SingleTasksRepository
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class AddTask @Inject constructor(var singleTasksRepository: SingleTasksRepository): BaseUseCase<Long, AddTask.Params>() {

    override suspend fun run(params: Params): Either<Failure, Long> {
       return try {
            withContext(Dispatchers.IO) {
                Either.Right(singleTasksRepository.addSingleTask(params.singleTask))
            }
        } catch(exp: Exception) {
            Either.Left(AddSingleTaskGroupFailure(exp))
        }
    }

    data class Params(val singleTask: SingleTaskEntry)
    data class AddSingleTaskGroupFailure(val error: Exception): Failure.FeatureFailure(error)
}