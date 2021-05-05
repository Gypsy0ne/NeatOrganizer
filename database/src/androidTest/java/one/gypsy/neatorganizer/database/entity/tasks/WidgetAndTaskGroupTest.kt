package one.gypsy.neatorganizer.database.entity.tasks

import one.gypsy.neatorganizer.database.DatabaseTest
import one.gypsy.neatorganizer.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.database.dao.tasks.TaskWidgetsDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

internal class WidgetAndTaskGroupTest : DatabaseTest() {

    private lateinit var taskWidgetsDao: TaskWidgetsDao
    private lateinit var taskGroupDao: SingleTaskGroupsDao

    @Before
    override fun setup() {
        super.setup()
        taskWidgetsDao = database.taskWidgetDao()
        taskGroupDao = database.singleTaskGroupsDao()
    }

    @Test
    fun shouldGetWidgetWithTaskGroupByIdObservable() {
        // given
        val widgetId = 22
        val taskGroupId = 12L
        val widget = TaskWidgetEntity(
            widgetId = widgetId,
            color = 11221122,
            taskGroupId = taskGroupId
        )
        val taskGroup = SingleTaskGroupEntity(
            "foobar",
            id = taskGroupId,
            createdAt = 123124
        )

        // when
        taskWidgetsDao.insert(widget)
        taskGroupDao.insert(taskGroup)
        val selectedWidgetWithTaskGroup = taskWidgetsDao.getWidgetWithTaskGroupByIdObservable(
            widgetId
        )

        // then
        selectedWidgetWithTaskGroup.observeForever {
            assertThat(it.widget).isEqualToComparingFieldByField(widget)
            assertThat(it.singleTaskGroup).isEqualToComparingFieldByField(taskGroup)
        }
    }

    @Test
    fun shouldGetWidgetWithTaskGroupByWidgetId() {
        // given
        val widgetId = 22
        val taskGroupId = 12L
        val widget = TaskWidgetEntity(
            widgetId = widgetId,
            color = 11221122,
            taskGroupId = taskGroupId
        )
        val taskGroup = SingleTaskGroupEntity(
            "foobar",
            id = taskGroupId,
            createdAt = 123124
        )

        // when
        taskWidgetsDao.insert(widget)
        taskGroupDao.insert(taskGroup)
        val selectedWidgetWithTaskGroup = taskWidgetsDao.getWidgetWithTaskGroupById(widgetId)

        // then
        assertThat(selectedWidgetWithTaskGroup.widget)
            .isEqualToComparingFieldByField(widget)
        assertThat(selectedWidgetWithTaskGroup.singleTaskGroup)
            .isEqualToComparingFieldByField(
                taskGroup
            )
    }

    @Test
    fun shouldUpdateLinkedTaskGroupById() {
        // given
        val widgetId = 22
        val taskGroupId = 12L
        val swappedTaskGroupId = 22L
        val widget = TaskWidgetEntity(
            widgetId = widgetId,
            color = 11221122,
            taskGroupId = taskGroupId
        )
        val taskGroups = arrayOf(
            SingleTaskGroupEntity(
                "foobar",
                id = taskGroupId,
                createdAt = 123124
            ),
            SingleTaskGroupEntity(
                "foobar",
                id = swappedTaskGroupId,
                createdAt = 123124
            )
        )

        // when
        taskWidgetsDao.insert(widget)
        taskGroupDao.insert(*taskGroups)
        taskWidgetsDao.updateLinkedTaskGroupById(widgetId, swappedTaskGroupId)
        val widgetWithTaskGroup = taskWidgetsDao.getWidgetWithTaskGroupById(widgetId)

        // then
        assertThat(widgetWithTaskGroup.singleTaskGroup.id).isEqualTo(swappedTaskGroupId)
    }
}
