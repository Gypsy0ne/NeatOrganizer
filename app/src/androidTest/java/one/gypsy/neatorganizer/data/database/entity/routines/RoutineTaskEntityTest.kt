package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineTasksDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutinesDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class RoutineTaskEntityTest {
    private lateinit var routineTasksDao: RoutineTasksDao
    private lateinit var routinesDao: RoutinesDao
    private lateinit var database: OrganizerDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            OrganizerDatabase::class.java
        ).build()
        routinesDao = database.routinesDao()
        routineTasksDao = database.routineTasksDao()
    }

    @After
    fun finish() {
        database.close()
    }

    @Test
    fun shouldInsertRoutineTask() {
        // given
        val routineTaskName = "foobar"
        val taskDone = true
        val routineTaskId = 1L
        val taskId = 1L
        val routineTask =
            RoutineTaskEntity(routineTaskName, taskDone, routineId = routineTaskId, id = taskId)
        routinesDao.insert(RoutineEntity("foobar", routineTaskId))

        // when
        routineTasksDao.insert(routineTask)

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).hasSize(1)
        selectedTasks.first().apply {
            assertThat(routineTaskName).isEqualTo(routineTaskName)
            assertThat(done).isEqualTo(taskDone)
            assertThat(routineId).isEqualTo(routineTaskId)
            assertThat(id).isEqualTo(taskId)
        }
    }

    @Test
    fun shouldInsertReplaceRoutineTask() {
        // given
        val routineTaskName = "foobar"
        val taskDone = true
        val routineTaskId = 1L
        val taskId = 1L
        val routineTask =
            RoutineTaskEntity(routineTaskName, taskDone, routineId = routineTaskId, id = taskId)
        val replacedRoutineTask =
            RoutineTaskEntity("replaced", false, routineId = routineTaskId, id = taskId)
        routinesDao.insert(RoutineEntity("foobar", routineTaskId))
        routineTasksDao.insert(replacedRoutineTask)

        // when
        routineTasksDao.insert(routineTask)

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).hasSize(1)
        selectedTasks.first().apply {
            assertThat(name).isEqualTo(routineTaskName)
            assertThat(done).isEqualTo(taskDone)
            assertThat(routineId).isEqualTo(routineTaskId)
            assertThat(id).isEqualTo(taskId)
        }
    }

    @Test
    fun shouldDeleteRoutineTask() {
        // given
        val routineTaskId = 1L
        val taskId = 1L
        val deletedTaskId = 2L
        val routineTask = RoutineTaskEntity("foobar", true, routineId = routineTaskId, id = taskId)
        val deletedTask =
            RoutineTaskEntity("deleted", false, routineId = routineTaskId, id = deletedTaskId)
        routinesDao.insert(RoutineEntity("foobar", routineTaskId))
        routineTasksDao.insert(deletedTask)
        routineTasksDao.insert(routineTask)

        // when
        routineTasksDao.delete(deletedTask)

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).hasSize(1)
        selectedTasks.first().apply {
            assertThat(id).isNotEqualTo(deletedTaskId)
            assertThat(id).isEqualTo(taskId)
        }
    }

    @Test
    fun shouldUpdateRoutineTask() {
        // given
        val taskDone = true
        val routineTaskId = 1L
        val taskId = 1L
        val routineTask =
            RoutineTaskEntity("foobar", taskDone, routineId = routineTaskId, id = taskId)
        val updatedRoutineTask =
            RoutineTaskEntity("replaced", false, routineId = routineTaskId, id = taskId)
        routinesDao.insert(RoutineEntity("foobar", routineTaskId))
        routineTasksDao.insert(routineTask)

        // when
        routineTasksDao.update(updatedRoutineTask)

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).hasSize(1)
        selectedTasks.first().apply {
            assertThat(name).isEqualTo(updatedRoutineTask.name)
            assertThat(done).isEqualTo(updatedRoutineTask.done)
            assertThat(routineId).isEqualTo(updatedRoutineTask.id)
            assertThat(id).isEqualTo(updatedRoutineTask.routineId)
        }
    }

    @Test
    fun shouldGetAllRoutineTasks() {
        // given
        val routineId = 1L
        val routine = RoutineEntity("foobar", routineId)
        val routineTasks = listOf(
            RoutineTaskEntity("taskOne", true, routineId = routineId, id = 1),
            RoutineTaskEntity("taskTwo", false, routineId, id = 2),
            RoutineTaskEntity("taskThree", true, routineId, id = 3),
            RoutineTaskEntity("taskFour", true, routineId, id = 4),
            RoutineTaskEntity("taskFive", false, routineId, id = 5)
        )
        routinesDao.insert(routine)

        // when
        routineTasksDao.insert(*routineTasks.toTypedArray())

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).containsExactlyInAnyOrderElementsOf(routineTasks)
    }

    @Test
    fun shouldResetTasksStatus() {
        // given
        val routineId = 1L
        val routine = RoutineEntity("foobar", routineId)
        val routineTasks = listOf(
            RoutineTaskEntity("taskOne", true, routineId, 1),
            RoutineTaskEntity("taskTwo", true, routineId, 2),
            RoutineTaskEntity("taskThree", true, routineId, 3),
            RoutineTaskEntity("taskFour", true, routineId, 4),
            RoutineTaskEntity("taskFive", true, routineId, 5)
        )
        routinesDao.insert(routine)
        routineTasksDao.insert(*routineTasks.toTypedArray())

        // when
        routineTasksDao.resetTasksStatus()

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks.map { it.done }).containsOnly(false)
    }

    @Test
    fun shouldDeleteRoutineWithTasks() {
        // given
        val routineId = 1L
        val routine = RoutineEntity("foobar", routineId)
        val routineTasks = listOf(
            RoutineTaskEntity("taskOne", true, routineId, 1),
            RoutineTaskEntity("taskTwo", true, routineId, 2),
            RoutineTaskEntity("taskThree", true, routineId, 3),
            RoutineTaskEntity("taskFour", true, routineId, 4),
            RoutineTaskEntity("taskFive", true, routineId, 5)
        )
        routinesDao.insert(routine)
        routineTasksDao.insert(*routineTasks.toTypedArray())

        // when
        routinesDao.delete(routine)

        // then
        val selectedTasks = routineTasksDao.getAllRoutineTasks()
        assertThat(selectedTasks).isEmpty()
    }

    @Test
    fun shouldMapEntityToDomainModel() {
        // given
        val routineTaskEntity = RoutineTaskEntity("foobar", true, routineId = 1, id = 1)

        //when
        val domainRoutineTask = routineTaskEntity.toRoutineTaskEntry()

        //then
        assertThat(routineTaskEntity.name).isEqualTo(domainRoutineTask.name)
        assertThat(routineTaskEntity.done).isEqualTo(domainRoutineTask.done)
        assertThat(routineTaskEntity.id).isEqualTo(domainRoutineTask.id)
        assertThat(routineTaskEntity.routineId).isEqualTo(domainRoutineTask.routineId)
    }
}