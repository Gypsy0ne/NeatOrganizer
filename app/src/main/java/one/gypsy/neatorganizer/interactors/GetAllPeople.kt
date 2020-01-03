package one.gypsy.neatorganizer.interactors

import one.gypsy.neatorganizer.data.PeopleRepository
import javax.inject.Inject

class GetAllPeople(@JvmField @Inject var peopleRepository: PeopleRepository) {
    suspend operator fun invoke() = peopleRepository.getAllPeople()
}