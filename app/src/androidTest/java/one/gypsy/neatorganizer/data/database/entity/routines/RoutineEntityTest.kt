package one.gypsy.neatorganizer.data.database.entity.routines

import one.gypsy.neatorganizer.data.database.DatabaseTest
import one.gypsy.neatorganizer.data.database.dao.routines.RoutinesDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class RoutineEntityTest : DatabaseTest() {

    private lateinit var routineEntityDao: RoutinesDao

    @Before
    override fun setup() {
        super.setup()
        routineEntityDao = database.routinesDao()
    }

    @Test
    fun shouldInsertRoutine() {
        // given
        val routineName = "foobar"
        val routine = RoutineEntity(routineName)

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
        val routine = RoutineEntity(routineName)

        // when
        routineEntityDao.insert(routine)
        val replacedRoutine =
            routineEntityDao.getAllRoutines().first().copy(name = replacedRoutineName)
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
        val routine = RoutineEntity(routineName)

        // when
        routineEntityDao.insert(routine)
        val updatedRoutine =
            routineEntityDao.getAllRoutines().first().copy(name = updatedRoutineName)
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
        val routine = RoutineEntity("foobar", 1)
        val routineToDelete = RoutineEntity(deletedRoutineName, deletedRoutineId)

        // when
        routineEntityDao.insert(routine, routineToDelete)
        routineEntityDao.delete(routineToDelete)

        // then
        val selectedRoutines = routineEntityDao.getAllRoutines()
        assertThat(selectedRoutines).hasSize(1)
        selectedRoutines.first().apply {
            assertThat(name).isNotEqualTo(deletedRoutineName)
            assertThat(id).isNotEqualTo(deletedRoutineId)
        }
    }

    @Test
    fun shouldGetAllRoutines() {
        // given
        val routines = listOf(
            RoutineEntity("name1", 1),
            RoutineEntity("name2", 2),
            RoutineEntity("name3", 3),
            RoutineEntity("name4", 4),
            RoutineEntity("name5", 5)
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
        val routineToDelete = RoutineEntity("name2", 2)
        val routines = listOf(
            RoutineEntity("name1", 1),
            routineToDelete,
            RoutineEntity("name3", 3),
            RoutineEntity("name4", 4),
            RoutineEntity("name5", 5)
        )

        // when
        routineEntityDao.insert(*routines.toTypedArray())
        routineEntityDao.deleteRoutineById(routineToDelete.id)

        // then
        val selectedRoutines = routineEntityDao.getAllRoutines()
        assertThat(selectedRoutines).doesNotContain(routineToDelete).hasSize(routines.size - 1)
    }

}