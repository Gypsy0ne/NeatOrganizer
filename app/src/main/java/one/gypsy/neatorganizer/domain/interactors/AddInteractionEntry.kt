package one.gypsy.neatorganizer.domain.interactors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.InteractionRepository
import one.gypsy.neatorganizer.domain.dto.InteractionEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure
import java.lang.Exception
import javax.inject.Inject

class AddInteractionEntry @Inject constructor(val interactionRepository: InteractionRepository): BaseUseCase<Long, AddInteractionEntry.Params>() {

    override suspend fun run(params: Params): Either<Failure, Long> {
        return try {
            withContext(Dispatchers.IO){
                interactionRepository.addInteractionEntry(params.interactionEntry)
                Either.Right(params.interactionEntry.profileId)
            }
        } catch (exp: Exception) {
            Either.Left(AddInteractionEntryFailure(exp))
        }
    }


    data class Params(val interactionEntry: InteractionEntry)
    data class AddInteractionEntryFailure(val error: Exception): Failure.FeatureFailure(error)
}