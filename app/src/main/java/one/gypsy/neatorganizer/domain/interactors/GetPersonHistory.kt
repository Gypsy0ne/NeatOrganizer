package one.gypsy.neatorganizer.domain.interactors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.PeopleRepository
import one.gypsy.neatorganizer.domain.dto.PersonHistory
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure
import java.lang.Exception
import javax.inject.Inject

class GetPersonHistory @Inject constructor(var peopleRepository: PeopleRepository): BaseUseCase<PersonHistory, GetPersonHistory.Params>() {

    override suspend fun run(params: Params): Either<Failure, PersonHistory> {
        return try {
            withContext(Dispatchers.IO){
//                Either.Right(peopleRepository.getPersonHsitory(params.personId))
                //TODO create proper usecase with separate repository, also create new Dao and Entity with one to one relation(single person has its own interaction history)
                Either.Right(PersonHistory(listOf()))
            }
        } catch (exp: Exception) {
            Either.Left(GetPersonHistoryFailure(exp))
        }
    }

    data class Params(val personId: Long)
    data class GetPersonHistoryFailure(val error: Exception): Failure.FeatureFailure(error)
}