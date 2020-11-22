package one.gypsy.neatorganizer.domain.datasource.people

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.database.dao.people.PeopleDao
import one.gypsy.neatorganizer.data.database.entity.people.PersonEntity
import one.gypsy.neatorganizer.domain.dto.people.InteractionEntry
import one.gypsy.neatorganizer.domain.dto.people.Person
import one.gypsy.neatorganizer.domain.dto.people.PersonEntry
import one.gypsy.neatorganizer.domain.dto.people.PersonProfile
import java.io.ByteArrayOutputStream

class UserCommunityDataSource(private val peopleDao: PeopleDao) :
    PeopleDataSource {

    override suspend fun add(personEntry: PersonEntry) =
        peopleDao.insert(
            PersonEntity(
                personEntry.name,
                personEntry.sex.name,
                convertBitmapToByteArray(personEntry.photoThumbnail),
                personEntry.lastInteraction,
                personEntry.dateOfBirth
            )
        )

    override suspend fun remove(personEntry: PersonEntry) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllPeopleEntries(): LiveData<List<PersonEntry>> =
        Transformations.map(peopleDao.getAllPeople()) {
            mapPersonEntitiesToEntries(it)
        }

    private fun mapPersonEntitiesToEntries(personEntities: List<PersonEntity>) = personEntities.map { personEntity ->
        PersonEntry(
            personEntity.id,
            personEntity.name,
            Person.Sex.valueOf(personEntity.sex),
            parseByteArrayToBitmap(personEntity.avatar),
            personEntity.lastInteraction,
            personEntity.dateOfBirth
        )
        }

    override suspend fun getPersonProfileById(personId: Long): LiveData<PersonProfile> =
        Transformations.map(peopleDao.getPersonProfileById(personId)) {
            PersonProfile(
                it.person.name,
                Person.Sex.valueOf(it.person.sex),
                parseByteArrayToBitmap(it.person.avatar),
                it.person.lastInteraction,
                it.person.dateOfBirth,
                it.interactionHistory.map { entry ->
                    InteractionEntry(
                        profileId = entry.personProfileId,
                        interactionDate = entry.creationDate,
                        rating = entry.rating
                    )
                }
            )
        }

    private fun parseByteArrayToBitmap(data: ByteArray?): Bitmap? =
        if (data != null) BitmapFactory.decodeByteArray(data, 0, data.size) else null

    private suspend fun convertBitmapToByteArray(bitmap: Bitmap?): ByteArray? =
        withContext(Dispatchers.Default) {
            ByteArrayOutputStream().let {
                if (bitmap != null && bitmap.compress(Bitmap.CompressFormat.JPEG, 70, it)) {
                    it.toByteArray()
                } else {
                    null
                }
            }
        }
}