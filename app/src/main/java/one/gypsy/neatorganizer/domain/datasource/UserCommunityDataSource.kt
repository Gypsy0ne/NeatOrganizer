package one.gypsy.neatorganizer.domain.datasource

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.database.dao.people.PeopleDao
import one.gypsy.neatorganizer.data.database.entity.people.PersonEntity
import one.gypsy.neatorganizer.domain.dto.Person
import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.domain.dto.PersonProfile
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class UserCommunityDataSource @Inject constructor(var peopleDao: PeopleDao) :
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
            it.map { personEntity ->
                PersonEntry(
                    personEntity.id,
                    personEntity.name,
                    Person.Sex.valueOf(personEntity.sex),
                    parseByteArrayToBitmap(personEntity.avatar),
                    personEntity.lastInteraction,
                    personEntity.dateOfBirth
                )
            }
        }

    override suspend fun getPersonProfileById(personId: Long): LiveData<PersonProfile> =
        Transformations.map(peopleDao.getPersonById(personId)) {
            PersonProfile(
                it.name,
                Person.Sex.valueOf(it.sex),
                parseByteArrayToBitmap(it.avatar),
                it.lastInteraction,
                it.dateOfBirth,
                listOf()
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