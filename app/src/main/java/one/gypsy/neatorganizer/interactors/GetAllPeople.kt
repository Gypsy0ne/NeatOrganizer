package one.gypsy.neatorganizer.interactors

import one.gypsy.neatorganizer.data.PeopleRepository
import javax.inject.Inject

class GetAllPeople @Inject constructor(var peopleRepository: PeopleRepository) {
    suspend operator fun invoke() = peopleRepository.getAllPeople()
}