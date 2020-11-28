package one.gypsy.neatorganizer.data.database.entity.tasks

import one.gypsy.neatorganizer.data.database.DatabaseTest
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class GroupWithSingleTasksTest : DatabaseTest() {

    private lateinit var tasksDao: SingleTasksDao
    private lateinit var taskGroupsDao: SingleTaskGroupsDao

    @Before
    override fun setup() {
        super.setup()
        tasksDao = database.singleTasksDao()
        taskGroupsDao = database.singleTaskGroupsDao()
    }

    @Test
    fun shouldGetAllGroupsWithSingleTasks() {
        // given
        val taskGroups = arrayOf(
            SingleTaskGroupEntity(name = "group1", id = 1L),
            SingleTaskGroupEntity(name = "group2", id = 23L),
            SingleTaskGroupEntity(name = "group3", id = 543L),
        )
        val tasks = arrayOf(
            listOf(
                SingleTaskEntity(groupId = 1L, id = 11L, name = "task1`", done = true),
                SingleTaskEntity(groupId = 1L, id = 12L, name = "task2", done = false),
                SingleTaskEntity(groupId = 1L, id = 999L, name = "task6", done = true)
            ),
            listOf(
                SingleTaskEntity(groupId = 23L, id = 31L, name = "task3", done = false),
                SingleTaskEntity(groupId = 23L, id = 51L, name = "task4", done = true)
            ),
            listOf(
                SingleTaskEntity(groupId = 543L, id = 641L, name = "task5", done = false)
            ),
        )

        // when
        taskGroupsDao.insert(*taskGroups)
        tasksDao.insert(*tasks.flatMap { it }.toTypedArray())
        val taskGroupsWithTasksObservable = taskGroupsDao.getAllGroupsWithSingleTasks()

        // then
        taskGroupsWithTasksObservable.observeForever { groupsWithTasks ->
            assertThat(groupsWithTasks.map { it.group }).containsExactlyInAnyOrderElementsOf(
                taskGroups.toList()
            )
            assertThat(groupsWithTasks.map { it.tasks }).containsExactlyInAnyOrderElementsOf(tasks.toList())
        }
    }

    @Test
    fun shouldGetGroupWithSingleTasksById() {
        // given
        val taskGroupId = 1L
        val tasks = arrayOf(
            SingleTaskEntity(groupId = taskGroupId, id = 11L, name = "foobar1", done = true),
            SingleTaskEntity(groupId = taskGroupId, id = 12L, name = "foobar2", done = false),
            SingleTaskEntity(groupId = taskGroupId, id = 33L, name = "foobar3", done = false),
        )
        val taskGroup = SingleTaskGroupEntity(name = "foobar", id = taskGroupId)

        // when
        taskGroupsDao.insert(taskGroup)
        tasksDao.insert(*tasks)
        val taskGroupWithTasksObservable = taskGroupsDao.getGroupWithSingleTasksById(taskGroupId)

        // then
        taskGroupWithTasksObservable.observeForever {
            assertThat(it.group).isEqualToComparingFieldByField(taskGroup)
            assertThat(it.tasks).containsExactlyInAnyOrderElementsOf(tasks.toList())
        }
    }
}