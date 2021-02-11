package one.gypsy.neatorganizer.domain.interactors.tasks

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.tasks.SingleTaskGroupsRepository

class GetAllSingleTaskGroupEntries(private val dataSource: SingleTaskGroupsRepository) :
    BaseUseCase<LiveData<List<SingleTaskGroupEntry>>, Unit>() {

    override suspend fun run(params: Unit): Either<Failure, LiveData<List<SingleTaskGroupEntry>>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(dataSource.getAllSingleTaskGroupEntries())
            }
        } catch (exp: Exception) {
            Either.Left(
                GetAllSingleTaskGroupEntriesFailure(
                    exp
                )
            )
        }
    }

    data class GetAllSingleTaskGroupEntriesFailure(val error: Exception) : Failure.FeatureFailure()
}
