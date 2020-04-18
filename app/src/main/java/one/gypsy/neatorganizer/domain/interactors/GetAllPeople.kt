package one.gypsy.neatorganizer.domain.interactors

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.PeopleRepository
import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure
import java.lang.Exception
import javax.inject.Inject



class GetAllPeople @Inject constructor(var peopleRepository: PeopleRepository): BaseUseCase<LiveData<List<PersonEntry>>, Unit>() {
    override suspend fun run(params: Unit): Either<Failure, LiveData<List<PersonEntry>>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(peopleRepository.getAllPeople())
            }
        } catch (exp: Exception) {
            Either.Left(GetAllPeopleFailure(exp))
        }
    }

    data class GetAllPeopleFailure(val error: Exception): Failure.FeatureFailure(error)
}