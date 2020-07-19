package one.gypsy.neatorganizer.data.repositories.people

import one.gypsy.neatorganizer.domain.datasource.people.PeopleDataSource
import one.gypsy.neatorganizer.domain.dto.people.PersonEntry

class PeopleRepository(var dataSource: PeopleDataSource) {

    suspend fun addPerson(personEntry: PersonEntry) = dataSource.add(personEntry)

    suspend fun removePerson(personEntry: PersonEntry) = dataSource.remove(personEntry)

    suspend fun getAllPeople() = dataSource.getAllPeopleEntries()

    suspend fun getPersonProfileById(personId: Long) = dataSource.getPersonProfileById(personId)

}