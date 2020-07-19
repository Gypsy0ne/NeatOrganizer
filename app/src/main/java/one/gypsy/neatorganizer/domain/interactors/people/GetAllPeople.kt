package one.gypsy.neatorganizer.domain.interactors.people

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.people.PeopleRepository
import one.gypsy.neatorganizer.domain.dto.people.PersonEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetAllPeople(var peopleRepository: PeopleRepository) :
    BaseUseCase<LiveData<List<PersonEntry>>, Unit>() {
    override suspend fun run(params: Unit): Either<Failure, LiveData<List<PersonEntry>>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(peopleRepository.getAllPeople())
            }
        } catch (exp: Exception) {
            Either.Left(
                GetAllPeopleFailure(
                    exp
                )
            )
        }
    }

    data class GetAllPeopleFailure(val error: Exception): Failure.FeatureFailure(error)
}