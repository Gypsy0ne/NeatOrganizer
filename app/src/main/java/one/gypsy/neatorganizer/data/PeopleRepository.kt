package one.gypsy.neatorganizer.data

import one.gypsy.neatorganizer.domain.Person
import javax.inject.Inject


class PeopleRepository @Inject constructor(var dataSource: PeopleDataSource) {

    suspend fun addPerson(person: Person) = dataSource.add(person)

    suspend fun removePerson(person: Person) = dataSource.remove(person)

    suspend fun getAllPeople() = dataSource.getAll()

//    suspend fun getPersonHsitory(personId: Long) = dataSource.getPersonHistory(personId)
}