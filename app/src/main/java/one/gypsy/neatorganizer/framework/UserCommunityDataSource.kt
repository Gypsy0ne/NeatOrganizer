package one.gypsy.neatorganizer.framework

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.PeopleDataSource
import one.gypsy.neatorganizer.database.dao.PersonDao
import one.gypsy.neatorganizer.database.entity.PersonEntity
import one.gypsy.neatorganizer.domain.Person
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class UserCommunityDataSource @Inject constructor(var personDao: PersonDao) : PeopleDataSource {


    override suspend fun add(person: Person) =
        personDao.insert(PersonEntity(person.name, convertBitmapToByteArray(person.photoThumbnail), person.lastInteraction, person.dateOfBirth))


    override suspend fun remove(person: Person) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAll(): LiveData<List<Person>> =
        Transformations.map(personDao.getAllPeople()) {
            it.map { personEntity ->
                Person(
                    personEntity.id,
                    personEntity.name,
                    parseByteArrayToBitmap(personEntity.avatar),
                    personEntity.lastInteraction,
                    personEntity.dateOfBirth
                )
            }
        }

    private fun parseByteArrayToBitmap(data: ByteArray?): Bitmap? = if (data != null) BitmapFactory.decodeByteArray(data, 0, data.size) else null

    //TODO run compressing function from worker thread
    private suspend fun convertBitmapToByteArray(bitmap: Bitmap?): ByteArray? = withContext(Dispatchers.Default) {
        ByteArrayOutputStream().let {
            if(bitmap != null && bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)) {
                it.toByteArray()
            } else {
                null
            }
        }
    }
}