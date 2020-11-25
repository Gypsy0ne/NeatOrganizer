package one.gypsy.neatorganizer.data.database.entity.tasks

import one.gypsy.neatorganizer.data.database.DatabaseTest
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class SingleTaskEntityTest : DatabaseTest() {

    private lateinit var tasksDao: SingleTasksDao
    private lateinit var taskGroupsDao: SingleTaskGroupsDao

    @Before
    override fun setup() {
        super.setup()
        tasksDao = database.singleTasksDao()
        taskGroupsDao = database.singleTaskGroupsDao()
    }

    @Test
    fun shouldInsertSingleTask() {
        // given
        val taskGroupId = 12L
        val singleTask = SingleTaskEntity(
            id = 10L,
            groupId = taskGroupId,
            name = "foobar",
            done = true
        )

        // when
        taskGroupsDao.insert(SingleTaskGroupEntity("bar", id = taskGroupId))
        tasksDao.insert(singleTask)
        val tasks = tasksDao.getAllSingleTasksByGroupId(taskGroupId)

        // then
        assertThat(tasks).hasSize(1)
        assertThat(tasks.first()).isEqualToComparingFieldByField(singleTask)
    }

    @Test
    fun shouldDeleteSingleTask() {
        // given
        val taskGroupId = 12L
        val singleTask = SingleTaskEntity(
            id = 10L,
            groupId = taskGroupId,
            name = "foobar",
            done = true
        )

        // when
        taskGroupsDao.insert(SingleTaskGroupEntity("bar", id = taskGroupId))
        tasksDao.insert(singleTask)
        tasksDao.delete(singleTask)
        val tasks = tasksDao.getAllSingleTasksByGroupId(taskGroupId)

        // then
        assertThat(tasks).hasSize(0)
    }

    @Test
    fun shouldUpdateSingleTask() {
        // given
        val taskGroupId = 12L
        val updatedTaskName = "updated"
        val singleTask =
            SingleTaskEntity(id = 10L, groupId = taskGroupId, name = "foobar", done = true)

        // when
        taskGroupsDao.insert(SingleTaskGroupEntity("bar", id = taskGroupId))
        tasksDao.insert(singleTask)
        tasksDao.update(singleTask.copy(name = updatedTaskName))
        val tasks = tasksDao.getAllSingleTasksByGroupId(taskGroupId)

        // then
        assertThat(tasks).hasSize(1)
        assertThat(tasks.first()).isEqualToComparingFieldByField()
    }

    @Test
    fun shouldGetAllSingleTasksByGroupId() {

    }

    @Test
    fun shouldGetAllSingleTasksByGroupIdObservable() {

    }

    @Test
    fun shouldProperlyMapToDomainModel() {

    }
}