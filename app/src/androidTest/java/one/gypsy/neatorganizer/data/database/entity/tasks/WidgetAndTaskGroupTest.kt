package one.gypsy.neatorganizer.data.database.entity.tasks

import one.gypsy.neatorganizer.data.database.DatabaseTest
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.TaskWidgetsDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class WidgetAndTaskGroupTest : DatabaseTest() {

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
        val taskGroup = SingleTaskGroupEntity("foobar", taskGroupId)

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
        val taskGroup = SingleTaskGroupEntity("foobar", taskGroupId)

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
            SingleTaskGroupEntity("foobar", taskGroupId),
            SingleTaskGroupEntity("foobar", swappedTaskGroupId)
        )

        // when
        taskWidgetsDao.insert(widget)
        taskGroupDao.insert(*taskGroups)
        taskWidgetsDao.updateLinkedTaskGroupById(widgetId, swappedTaskGroupId)
        val widgetWithTaskGroup = taskWidgetsDao.getWidgetWithTaskGroupById(widgetId)

        // then
        assertThat(widgetWithTaskGroup.singleTaskGroup.id).isEqualTo(swappedTaskGroupId)
    }

    @Test
    fun shouldMapToDomainModel() {
        // given
        val taskWidget = WidgetAndTaskGroup(
            widget = TaskWidgetEntity(widgetId = 13, taskGroupId = 12L, color = 121233),
            singleTaskGroup = SingleTaskGroupEntity(name = "foobar", id = 12L)
        )

        // when
        val domainWidgetAndGroup = taskWidget.toTitledWidgetTaskEntry()

        // then
        with(domainWidgetAndGroup) {
            assertThat(taskGroupTitle).isEqualTo(taskWidget.singleTaskGroup.name)
            assertThat(appWidgetId).isEqualTo(taskWidget.widget.widgetId)
            assertThat(widgetColor).isEqualTo(taskWidget.widget.color)
            assertThat(taskGroupId).isEqualTo(taskWidget.widget.taskGroupId)
        }
    }
}