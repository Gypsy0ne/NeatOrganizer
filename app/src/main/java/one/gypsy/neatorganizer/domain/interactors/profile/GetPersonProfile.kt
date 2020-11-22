package one.gypsy.neatorganizer.domain.interactors.profile

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.people.PeopleRepository
import one.gypsy.neatorganizer.domain.dto.people.PersonProfile
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetPersonProfile(private val peopleRepository: PeopleRepository) :
    BaseUseCase<LiveData<PersonProfile>, GetPersonProfile.Params>() {
    override suspend fun run(params: Params): Either<Failure, LiveData<PersonProfile>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(peopleRepository.getPersonProfileById(params.personId))
            }
        } catch (exp: Exception) {
            Either.Left(
                GetPersonProfileFailure(
                    exp
                )
            )
        }

    }
    data class Params(val personId: Long)
    data class GetPersonProfileFailure(val error: Exception): Failure.FeatureFailure(error)
}