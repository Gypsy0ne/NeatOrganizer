package one.gypsy.neatorganizer.data.database.entity.routines.reset

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSnapshotsDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

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
    fun shouldInsertReplaceRoutineSnapshot() {
        // given
        val tasksDone = 13
        val snapshot =
            RoutineSnapshotEntity(
                tasksOverall = 27,
                tasksDone = tasksDone,
                routinesResetDate = Date()
            )

        // when
        routineSnapshotDao.insert(snapshot)
        val modifiedTasksOverall = 33
        val modifiedResetDate = Date(3600)
        val modifiedSnapshot = routineSnapshotDao.getAllRoutineSnapshots().first().copy(
            tasksOverall = modifiedTasksOverall,
            routinesResetDate = modifiedResetDate
        )
        routineSnapshotDao.insert(modifiedSnapshot)

        // then
        val selectionResult = routineSnapshotDao.getAllRoutineSnapshots()
        assertThat(selectionResult).hasSize(1)
        selectionResult.first().also {
            assertThat(it.tasksOverall).isEqualTo(modifiedTasksOverall)
            assertThat(it.tasksDone).isEqualTo(tasksDone)
            assertThat(it.routinesResetDate).isEqualTo(modifiedResetDate)
        }
    }

    @Test
    fun shouldInsertRoutineSnapshot() {
        // given
        val snapshot =
            RoutineSnapshotEntity(tasksOverall = 27, tasksDone = 13, routinesResetDate = Date())

        // when
        routineSnapshotDao.insert(snapshot)

        // then
        val selectionResult = routineSnapshotDao.getAllRoutineSnapshots()
        assertThat(selectionResult).hasSize(1)
        selectionResult.first().also {
            assertThat(it.tasksOverall).isEqualTo(snapshot.tasksOverall)
            assertThat(it.tasksDone).isEqualTo(snapshot.tasksDone)
            assertThat(it.routinesResetDate).isEqualTo(snapshot.routinesResetDate)
        }
    }

    @Test
    fun shouldRemoveRoutineSnapshot() {
        // given
        val deletedSnapshotTasksOverall = 27
        val deletedSnapshotTasksDone = 27
        val deletedSnapshotResetDate = Date(3500012)
        val snapshot =
            RoutineSnapshotEntity(tasksOverall = 21, tasksDone = 13, routinesResetDate = Date())
        val deletedSnapshot =
            RoutineSnapshotEntity(
                tasksOverall = deletedSnapshotTasksOverall,
                tasksDone = deletedSnapshotTasksDone,
                routinesResetDate = deletedSnapshotResetDate
            )

        // when
        routineSnapshotDao.apply {
            insert(snapshot)
            insert(deletedSnapshot)
        }
        routineSnapshotDao.getAllRoutineSnapshots()
            .find { it.tasksDone == deletedSnapshotTasksDone }?.let {
                routineSnapshotDao.delete(it)
            }

        // then
        val selectionResult = routineSnapshotDao.getAllRoutineSnapshots()
        assertThat(selectionResult).hasSize(1)
        selectionResult.first().also {
            assertThat(it.tasksOverall).isNotEqualTo(deletedSnapshotTasksOverall)
            assertThat(it.tasksDone).isNotEqualTo(deletedSnapshotTasksDone)
            assertThat(it.routinesResetDate).isNotEqualTo(deletedSnapshotResetDate)
        }
    }

    @Test
    fun shouldUpdateRoutineSnapshot() {
        // given
        val modifiedTasksDone = 99
        val modifiedTasksOverall = 99
        val snapshot =
            RoutineSnapshotEntity(tasksOverall = 27, tasksDone = 12, routinesResetDate = Date())

        // when
        routineSnapshotDao.insert(snapshot)
        val modifiedSnapshot = routineSnapshotDao.getAllRoutineSnapshots().first()
            .copy(tasksOverall = modifiedTasksOverall, tasksDone = modifiedTasksDone)
        routineSnapshotDao.update(modifiedSnapshot)

        // then
        val selectionResult = routineSnapshotDao.getAllRoutineSnapshots()
        assertThat(selectionResult).hasSize(1)
        selectionResult.first().also {
            assertThat(it.tasksOverall).isEqualTo(modifiedTasksOverall)
            assertThat(it.tasksDone).isEqualTo(modifiedTasksDone)
        }
    }

    @Test
    fun shouldSelectAllRoutineSnapshot() {
        // given
        val snapshots = listOf(
            RoutineSnapshotEntity(
                tasksOverall = 27,
                tasksDone = 12,
                routinesResetDate = Date(),
                routineSnapshotId = 1
            ),
            RoutineSnapshotEntity(
                tasksOverall = 17,
                tasksDone = 2,
                routinesResetDate = Date(),
                routineSnapshotId = 2
            ),
            RoutineSnapshotEntity(
                tasksOverall = 123,
                tasksDone = 123,
                routinesResetDate = Date(),
                routineSnapshotId = 3
            ),
            RoutineSnapshotEntity(
                tasksOverall = 144,
                tasksDone = 124,
                routinesResetDate = Date(),
                routineSnapshotId = 4
            )
        )

        // when
        routineSnapshotDao.insert(*snapshots.toTypedArray())

        // then
        assertThat(routineSnapshotDao.getAllRoutineSnapshots()).hasSize(snapshots.size)
            .containsExactlyInAnyOrderElementsOf(snapshots)
    }


}