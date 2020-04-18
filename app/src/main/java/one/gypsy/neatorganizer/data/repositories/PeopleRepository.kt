package one.gypsy.neatorganizer.data.repositories

import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.domain.datasource.PeopleDataSource
import javax.inject.Inject


class PeopleRepository @Inject constructor(var dataSource: PeopleDataSource) {

    suspend fun addPerson(personEntry: PersonEntry) = dataSource.add(personEntry)

    suspend fun removePerson(personEntry: PersonEntry) = dataSource.remove(personEntry)

    suspend fun getAllPeople() = dataSource.getAllPeopleEntries()

    suspend fun getPersonProfileById(personId: Long) = dataSource.getPersonProfileById(personId)

//    suspend fun getPersonHsitory(personId: Long) = dataSource.getPersonHistory(personId)
}