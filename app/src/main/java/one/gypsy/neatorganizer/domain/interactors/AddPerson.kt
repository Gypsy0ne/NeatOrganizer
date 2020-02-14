package one.gypsy.neatorganizer.domain.interactors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.PeopleRepository
import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure
import java.lang.Exception
import javax.inject.Inject

class AddPerson @Inject constructor(var peopleRepository: PeopleRepository): BaseUseCase<Unit, AddPerson.Params>() {
    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO){
                Either.Right(peopleRepository.addPerson(params.personEntry))
            }
        } catch (exp: Exception) {
            Either.Left(AddPersonFailure(exp))
        }
    }

    data class Params(val personEntry: PersonEntry)
    data class AddPersonFailure(val error: Exception): Failure.FeatureFailure(error)
}