package one.gypsy.neatorganizer.interactors

import one.gypsy.neatorganizer.data.PeopleRepository
import one.gypsy.neatorganizer.domain.Person
import javax.inject.Inject

class AddPerson(@JvmField @Inject var peopleRepository: PeopleRepository) {
    suspend operator fun invoke(person: Person) = peopleRepository.addPerson(person)
}