package one.gypsy.neatorganizer.interactors

import one.gypsy.neatorganizer.data.PeopleRepository
import one.gypsy.neatorganizer.domain.Person
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure
import java.lang.Exception
import javax.inject.Inject

class AddPerson @Inject constructor(var peopleRepository: PeopleRepository): BaseUseCase<Unit, AddPerson.Params>() {
    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            Either.Right(peopleRepository.addPerson(params.person))
        } catch (exp: Exception) {
            Either.Left(AddPersonFailure(exp))
        }
    }

    data class Params(val person: Person)
    data class AddPersonFailure(val error: Exception): Failure.FeatureFailure(error)
}