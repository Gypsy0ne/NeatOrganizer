package one.gypsy.neatorganizer.database.entity.routines.reset

import one.gypsy.neatorganizer.database.DatabaseTest
import one.gypsy.neatorganizer.database.dao.routines.RoutineSnapshotsDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.util.Date

internal class RoutineSnapshotEntityTest : DatabaseTest() {
    private lateinit var routineSnapshotDao: RoutineSnapshotsDao

    @Before
    override fun setup() {
        super.setup()
        routineSnapshotDao = database.routineSnapshotsDao()
    }

    @Test
    fun shouldSelectLastResetEntry() {
        // given
        val resetEntries = arrayOf(
            RoutineSnapshotEntity(routineSnapshotId = 1, routinesResetDate = Date(200)),
            RoutineSnapshotEntity(routineSnapshotId = 2, routinesResetDate = Date(500)),
            RoutineSnapshotEntity(routineSnapshotId = 3, routinesResetDate = Date(800)),
        )

        // when
        routineSnapshotDao.insert(*resetEntries)
        val lastEntry = routineSnapshotDao.getLastResetEntry()

        // then
        assertThat(lastEntry?.routineSnapshotId).isEqualTo(3)
        assertThat(lastEntry?.routinesResetDate?.time).isEqualTo(800)
    }

    @Test
    fun shouldGetLastResetEntry() {
        // given
        val resetEntries = arrayOf(
            RoutineSnapshotEntity(routineSnapshotId = 1, routinesResetDate = Date(200)),
            RoutineSnapshotEntity(routineSnapshotId = 2, routinesResetDate = Date(500)),
            RoutineSnapshotEntity(routineSnapshotId = 3, routinesResetDate = Date(800)),
        )

        // when
        routineSnapshotDao.insert(*resetEntries)
        val lastEntry = routineSnapshotDao.getLastResetEntry()

        // then
        assertThat(lastEntry?.routineSnapshotId).isEqualTo(3)
        assertThat(lastEntry?.routinesResetDate?.time).isEqualTo(800)
    }

    @Test
    fun shouldInsertReplaceRoutineSnapshot() {
        // given
        val snapshot =
            RoutineSnapshotEntity(
                routinesResetDate = Date()
            )

        // when
        routineSnapshotDao.insert(snapshot)
        val modifiedResetDate = Date(3600)
        val modifiedSnapshot = routineSnapshotDao.getAllRoutineSnapshots().first().copy(
            routinesResetDate = modifiedResetDate
        )
        val modifiedSnapshotId = modifiedSnapshot.routineSnapshotId
        routineSnapshotDao.insert(modifiedSnapshot)
        val selectionResult = routineSnapshotDao.getAllRoutineSnapshots()

        // then
        assertThat(selectionResult).hasSize(1)
        selectionResult.first().also {
            assertThat(it.routinesResetDate).isEqualTo(modifiedResetDate)
            assertThat(it.routineSnapshotId).isEqualTo(modifiedSnapshotId)
        }
    }

    @Test
    fun shouldInsertRoutineSnapshot() {
        // given
        val snapshot =
            RoutineSnapshotEntity(
                routinesResetDate = Date()
            )

        // when
        routineSnapshotDao.insert(snapshot)
        val selectionResult = routineSnapshotDao.getAllRoutineSnapshots()

        // then
        assertThat(selectionResult).hasSize(1)
        selectionResult.first().also {
            assertThat(it.routinesResetDate).isEqualTo(snapshot.routinesResetDate)
        }
    }

    @Test
    fun shouldRemoveRoutineSnapshot() {
        // given
        val deletedSnapshotResetDate = Date(3500012)
        val snapshot =
            RoutineSnapshotEntity(
                routinesResetDate = Date()
            )
        val deletedSnapshot =
            RoutineSnapshotEntity(
                routinesResetDate = deletedSnapshotResetDate
            )

        // when
        routineSnapshotDao.apply {
            insert(snapshot)
            insert(deletedSnapshot)
        }
        routineSnapshotDao.getAllRoutineSnapshots()
            .find { it.routinesResetDate == deletedSnapshot.routinesResetDate }?.let {
                routineSnapshotDao.delete(it)
            }
        val selectionResult = routineSnapshotDao.getAllRoutineSnapshots()

        // then
        assertThat(selectionResult).hasSize(1)
        selectionResult.first().also {
            assertThat(it.routinesResetDate).isNotEqualTo(deletedSnapshotResetDate)
        }
    }

    @Test
    fun shouldUpdateRoutineSnapshot() {
        // given
        val snapshot =
            RoutineSnapshotEntity(
                routinesResetDate = Date()
            )
        val modifiedResetDate = Date(123)

        // when
        routineSnapshotDao.insert(snapshot)
        val modifiedSnapshot = routineSnapshotDao.getAllRoutineSnapshots().first()
            .copy(routinesResetDate = modifiedResetDate)
        val modifiedSnapshotId = modifiedSnapshot.routineSnapshotId
        routineSnapshotDao.update(modifiedSnapshot)
        val selectionResult = routineSnapshotDao.getAllRoutineSnapshots()

        // then
        assertThat(selectionResult).hasSize(1)
        selectionResult.first().also {
            assertThat(it.routineSnapshotId).isEqualTo(modifiedSnapshotId)
            assertThat(it.routinesResetDate).isEqualTo(modifiedResetDate)
        }
    }

    @Test
    fun shouldSelectAllRoutineSnapshot() {
        // given
        val snapshots = listOf(
            RoutineSnapshotEntity(
                routinesResetDate = Date(),
                routineSnapshotId = 1
            ),
            RoutineSnapshotEntity(
                routinesResetDate = Date(),
                routineSnapshotId = 2
            ),
            RoutineSnapshotEntity(
                routinesResetDate = Date(),
                routineSnapshotId = 3
            ),
            RoutineSnapshotEntity(
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
