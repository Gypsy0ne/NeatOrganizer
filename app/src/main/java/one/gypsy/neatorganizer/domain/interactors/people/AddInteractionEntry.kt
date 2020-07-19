package one.gypsy.neatorganizer.domain.interactors.people

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.people.InteractionRepository
import one.gypsy.neatorganizer.domain.dto.people.InteractionEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class AddInteractionEntry(val interactionRepository: InteractionRepository) :
    BaseUseCase<Unit, AddInteractionEntry.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                interactionRepository.addInteractionEntry(params.interactionEntry)
                Either.Right(Unit)
            }
        } catch (exp: Exception) {
            Either.Left(
                AddInteractionEntryFailure(
                    exp
                )
            )
        }
    }


    data class Params(val interactionEntry: InteractionEntry)
    data class AddInteractionEntryFailure(val error: Exception): Failure.FeatureFailure(error)
}