package one.gypsy.neatorganizer.data.database.entity.routines.reset

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSnapshotsDao
import org.junit.After
import org.junit.Before
import org.junit.Test

class RoutineSnapshotEntityTest {
    private lateinit var routineSnapshotDao: RoutineSnapshotsDao
    private lateinit var database: OrganizerDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            OrganizerDatabase::class.java
        ).build()
        routineSnapshotDao = database.routineSnapshotsDao()
    }

    @After
    fun finish() {
        database.close()
    }

    @Test
    fun shouldMakeMeSmile() {
    }
}