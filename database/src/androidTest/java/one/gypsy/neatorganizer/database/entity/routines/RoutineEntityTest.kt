package one.gypsy.neatorganizer.database.entity.routines

import one.gypsy.neatorganizer.domain.database.DatabaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class RoutineEntityTest : DatabaseTest() {

    private lateinit var routineEntityDao: one.gypsy.neatorganizer.database.dao.routines.RoutinesDao

    @Before
    override fun setup() {
        super.setup()
        routineEntityDao = database.routinesDao()
    }

    @Test
    fun shouldInsertRoutine() {
        // given
        val routineName = "foobar"
        val routine = RoutineEntity(
            routineName,
            createdAt = 123124
        )

        // when
        routineEntityDao.insert(routine)

        // then
        val selectedRoutines = routineEntityDao.getAllRoutines()
        assertThat(selectedRoutines).hasSize(1)
        assertThat(selectedRoutines.first().name).isEqualTo(routineName)
    }

    @Test
    fun shouldInsertReplaceRoutine() {
        // given
        val routineName = "foobar"
        val replacedRoutineName = "foobar2"
        val routine = RoutineEntity(
            routineName,
            createdAt = 123124
        )

        // when
        routineEntityDao.insert(routine)
        val replacedRoutine = routineEntityDao
            .getAllRoutines()
            .first()
            .copy(name = replacedRoutineName)
        val replacedRoutineId = replacedRoutine.id
        routineEntityDao.insert(replacedRoutine)

        // then
        val selectedRoutines = routineEntityDao.getAllRoutines()
        assertThat(selectedRoutines).hasSize(1)
        selectedRoutines.first().apply {
            assertThat(name).isEqualTo(replacedRoutineName)
            assertThat(id).isEqualTo(replacedRoutineId)
        }
    }

    @Test
    fun shouldUpdateRoutine() {
        // given
        val routineName = "foobar"
        val updatedRoutineName = "foobar2"
        val routine = RoutineEntity(
            routineName,
            createdAt = 123124
        )

        // when
        routineEntityDao.insert(routine)
        val updatedRoutine = routineEntityDao
            .getAllRoutines()
            .first()
            .copy(name = updatedRoutineName)
        val updatedRoutineId = updatedRoutine.id
        routineEntityDao.insert(updatedRoutine)

        // then
        val selectedRoutines = routineEntityDao.getAllRoutines()
        assertThat(selectedRoutines).hasSize(1)
        selectedRoutines.first().apply {
            assertThat(name).isEqualTo(updatedRoutine.name)
            assertThat(id).isEqualTo(updatedRoutineId)
        }
    }

    @Test
    fun shouldDeleteRoutine() {
        // given
        val deletedRoutineName = "to delete"
        val deletedRoutineId = 2L
        val routine = RoutineEntity(
            "foobar",
            id = 1,
            createdAt = 123124
        )
        val routineToDelete = RoutineEntity(
            deletedRoutineName,
            id = deletedRoutineId,
            createdAt = 123124
        )

        // when
        routineEntityDao.insert(routine, routineToDelete)
        routineEntityDao.delete(routineToDelete)

        // then
        val selectedRoutines = routineEntityDao.getAllRoutines()
        assertThat(selectedRoutines).hasSize(1)
        with(selectedRoutines.first()) {
            assertThat(name).isNotEqualTo(deletedRoutineName)
            assertThat(id).isNotEqualTo(deletedRoutineId)
        }
    }

    @Test
    fun shouldGetAllRoutines() {
        // given
        val routines = listOf(
            RoutineEntity(
                "name1",
                id = 1,
                createdAt = 123124
            ),
            RoutineEntity(
                "name2",
                id = 2,
                createdAt = 123124
            ),
            RoutineEntity(
                "name3",
                id = 3,
                createdAt = 123124
            ),
            RoutineEntity(
                "name4",
                id = 4,
                createdAt = 123124
            ),
            RoutineEntity(
                "name5",
                id = 5,
                createdAt = 123124
            )
        )

        // when
        routineEntityDao.insert(*routines.toTypedArray())

        // then
        val selectedRoutines = routineEntityDao.getAllRoutines()
        assertThat(selectedRoutines).containsExactlyInAnyOrderElementsOf(routines)
    }

    @Test
    fun shouldDeleteRoutineById() {
        // given
        val routineToDelete = RoutineEntity(
            name = "name2",
            id = 2,
            createdAt = 12
        )
        val routines = listOf(
            RoutineEntity(
                "name1",
                id = 1,
                createdAt = 13
            ),
            routineToDelete,
            RoutineEntity(
                "name3",
                id = 3,
                createdAt = 523
            ),
            RoutineEntity(
                "name4",
                id = 4,
                createdAt = 15243
            ),
            RoutineEntity(
                "name5",
                id = 5,
                createdAt = 14225
            )
        )

        // when
        routineEntityDao.insert(*routines.toTypedArray())
        routineEntityDao.deleteRoutineById(routineToDelete.id)

        // then
        val selectedRoutines = routineEntityDao.getAllRoutines()
        assertThat(selectedRoutines).doesNotContain(routineToDelete).hasSize(routines.size - 1)
    }
}
