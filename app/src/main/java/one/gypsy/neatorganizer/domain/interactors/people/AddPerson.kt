package one.gypsy.neatorganizer.domain.interactors.people

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.people.PeopleRepository
import one.gypsy.neatorganizer.domain.dto.people.PersonEntry
import one.gypsy.neatorganizer.domain.interactors.people.AddPerson.Params
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class AddPerson(private val peopleRepository: PeopleRepository) :
    BaseUseCase<Long, Params>() {

    override suspend fun run(params: Params): Either<Failure, Long> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(peopleRepository.addPerson(params.personEntry))
            }
        } catch (exp: Exception) {
            Either.Left(
                AddPersonFailure(
                    exp
                )
            )
        }
    }

    data class Params(val personEntry: PersonEntry)
    data class AddPersonFailure(val error: Exception): Failure.FeatureFailure(error)
}