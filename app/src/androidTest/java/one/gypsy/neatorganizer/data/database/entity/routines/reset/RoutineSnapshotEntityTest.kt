package one.gypsy.neatorganizer.data.database.entity.routines.reset

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSnapshotDaysDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSnapshotTasksDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSnapshotsDao
import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshotDay
import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshotTask
import one.gypsy.neatorganizer.domain.dto.routines.reset.toRoutineSnapshotDayEntity
import one.gypsy.neatorganizer.domain.dto.routines.reset.toRoutineSnapshotTaskEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class RoutineSnapshotEntityTest {
    private lateinit var routineSnapshotDao: RoutineSnapshotsDao
    private lateinit var routineSnapshotDaysDao: RoutineSnapshotDaysDao
    private lateinit var routineSnapshotTasksDao: RoutineSnapshotTasksDao
    private lateinit var database: OrganizerDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            OrganizerDatabase::class.java
        ).build()
        routineSnapshotDao = database.routineSnapshotsDao()
        routineSnapshotDaysDao = database.routineSnapshotDaysDao()
        routineSnapshotTasksDao = database.routineSnapshotTasksDao()
    }

    @After
    fun finish() {
        database.close()
    }

    @Test
    fun shouldMakeMeSmile() {
        // given
        val snapshotDay = RoutineSnapshotDay(1, 7, Date()).toRoutineSnapshotDayEntity()
        val snapshotTask1 = RoutineSnapshotTask(true, "lalal1").toRoutineSnapshotTaskEntity()
        val snapshotTask2 = RoutineSnapshotTask(true, "lalal2").toRoutineSnapshotTaskEntity()
        val snapshotTask3 = RoutineSnapshotTask(true, "lalal3").toRoutineSnapshotTaskEntity()

        // when
        routineSnapshotDaysDao.addDayWithTasks(
            snapshotDay,
            listOf(snapshotTask1, snapshotTask2, snapshotTask3)
        )

        // then
        val result = routineSnapshotDaysDao.getSnapshotDaysWithTasks()
    }
}