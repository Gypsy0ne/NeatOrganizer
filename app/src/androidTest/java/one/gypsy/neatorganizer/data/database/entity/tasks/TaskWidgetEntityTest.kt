package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.TaskWidgetsDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TaskWidgetEntityTest {

    private lateinit var taskWidgetsDao: TaskWidgetsDao
    private lateinit var taskGroupDao: SingleTaskGroupsDao
    private lateinit var database: OrganizerDatabase

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            OrganizerDatabase::class.java
        ).build()
        taskWidgetsDao = database.taskWidgetDao()
        taskGroupDao = database.singleTaskGroupsDao()
    }

    @After
    fun finish() {
        database.close()
    }

    @Test
    fun shouldUpdateTaskWidget() {
        // given
        val updatedTaskGroupId = 423L
        val taskWidget = TaskWidgetEntity(widgetId = 312, taskGroupId = 12, color = 312221)

        // when
        taskWidgetsDao.insert(taskWidget)
        taskWidgetsDao.update(taskWidget.copy(taskGroupId = updatedTaskGroupId))
        val updatedTaskWidget = taskWidgetsDao.getWidgetById(taskWidget.widgetId)

        // then
        assertThat(updatedTaskWidget.taskGroupId).isEqualTo(updatedTaskGroupId)
    }

    @Test
    fun shouldRemoveTaskWidget() {
        // given
        val taskWidget = TaskWidgetEntity(widgetId = 312, taskGroupId = 12, color = 312221)

        // when
        taskWidgetsDao.insert(taskWidget)
        taskWidgetsDao.delete(taskWidget)
        val taskWidgetIds = taskWidgetsDao.getAllTaskWidgets()

        // then
        assertThat(taskWidgetIds).isEmpty()
    }

    @Test
    fun shouldInsertTaskWidget() {
        // given
        val taskWidget = TaskWidgetEntity(widgetId = 312, taskGroupId = 12, color = 312221)

        // when
        taskWidgetsDao.insert(taskWidget)
        val fetchedTaskWidgets = taskWidgetsDao.getAllTaskWidgets()

        // then
        assertThat(fetchedTaskWidgets).hasSize(1)
        assertThat(fetchedTaskWidgets.first()).isEqualToComparingFieldByField(taskWidget)
    }

    @Test
    fun shouldGetWidgetById() {
        // given
        val taskWidget = TaskWidgetEntity(widgetId = 312, taskGroupId = 12, color = 312221)

        // when
        taskWidgetsDao.insert(taskWidget)
        val fetchedTaskWidget = taskWidgetsDao.getWidgetById(taskWidget.widgetId)

        // then
        assertThat(fetchedTaskWidget).isEqualToComparingFieldByField(taskWidget)
    }

    @Test
    fun shouldGetWidgetWithTaskGroupByWidgetId() {
        // given
        val widgetId = 22
        val widgetColor = 11221122
        val taskGroupId = 12L
        val widget = TaskWidgetEntity(
            widgetId = widgetId,
            color = widgetColor,
            taskGroupId = taskGroupId
        )
        val taskGroup = SingleTaskGroupEntity("foobar", taskGroupId)

        // when
        taskWidgetsDao.insert(widget)
        taskGroupDao.insert(taskGroup)
        val selectedWidgetWithTaskGroup = taskWidgetsDao.getWidgetWithTaskGroupById(widgetId)

        // then
        assertThat(selectedWidgetWithTaskGroup.widget).isEqualToComparingFieldByField(widget)
        assertThat(selectedWidgetWithTaskGroup.singleTaskGroup).isEqualToComparingFieldByField(
            taskGroup
        )
    }

    @Test
    fun shouldGetWidgetWithTaskGroupByIdObservable() {
        // given
        val widgetId = 22
        val widgetColor = 11221122
        val taskGroupId = 12L
        val widget = TaskWidgetEntity(
            widgetId = widgetId,
            color = widgetColor,
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
    fun shouldGetAllWidgetIds() {
        // given
        val widgets = listOf(
            TaskWidgetEntity(widgetId = 12, color = 123123, taskGroupId = 66L),
            TaskWidgetEntity(widgetId = 13, color = 123132, taskGroupId = 62L),
            TaskWidgetEntity(widgetId = 14, color = 123634, taskGroupId = 64L),
            TaskWidgetEntity(widgetId = 15, color = 123634, taskGroupId = 56L)
        )

        // when
        taskWidgetsDao.insert(*widgets.toTypedArray())
        val taskWidgetIds = taskWidgetsDao.getAllWidgetIds()

        // then
        assertThat(taskWidgetIds).containsExactlyInAnyOrder(*widgets.map { it.widgetId }
            .toIntArray())
    }

    @Test
    fun shouldDeleteWidgetById() {
        // given
        val deletedWidgetId = 1231
        val widget = TaskWidgetEntity(widgetId = deletedWidgetId, taskGroupId = 99L, color = 768876)

        // when
        taskWidgetsDao.insert(widget)
        taskWidgetsDao.deleteWidgetById(deletedWidgetId)
        val widgets = taskWidgetsDao.getAllTaskWidgets()

        // then
        assertThat(widgets).isEmpty()
    }

    @Test
    fun shouldUpdateLinkedTaskGroupById() {
        // given
        val widgetId = 22
        val widgetColor = 11221122
        val taskGroupId = 12L
        val swappedTaskGroupId = 22L
        val widget = TaskWidgetEntity(
            widgetId = widgetId,
            color = widgetColor,
            taskGroupId = taskGroupId
        )
        val taskGroups = listOf(
            SingleTaskGroupEntity("foobar", taskGroupId),
            SingleTaskGroupEntity("foobar", swappedTaskGroupId)
        )

        // when
        taskWidgetsDao.insert(widget)
        taskGroupDao.insert(*taskGroups.toTypedArray())
        taskWidgetsDao.updateLinkedTaskGroupById(widgetId, swappedTaskGroupId)
        val widgetWithTaskGroup = taskWidgetsDao.getWidgetWithTaskGroupById(widgetId)

        // then
        assertThat(widgetWithTaskGroup.singleTaskGroup.id).isEqualTo(swappedTaskGroupId)
    }

    @Test
    fun shouldGetAllTaskWidgetsObservable() {
        // given
        val taskWidgets = listOf(
            TaskWidgetEntity(widgetId = 12, color = 3456786, taskGroupId = 35L),
            TaskWidgetEntity(widgetId = 22, color = 3456786, taskGroupId = 38L),
            TaskWidgetEntity(widgetId = 52, color = 3456786, taskGroupId = 32L),
            TaskWidgetEntity(widgetId = 2, color = 3456786, taskGroupId = 31L),
        )

        // when
        taskWidgetsDao.insert(*taskWidgets.toTypedArray())
        val taskWidgetsObservable = taskWidgetsDao.getAllTaskWidgetsObservable()

        // then
        taskWidgetsObservable.observeForever {
            assertThat(it).containsExactlyInAnyOrderElementsOf(taskWidgets)
        }
    }

    @Test
    fun shouldGetTaskGroupIdByWidgetId() {
        // given
        val widgetTaskGroupId = 13L
        val taskWidgetId = 1

        // when
        taskWidgetsDao.insert(
            TaskWidgetEntity(
                widgetId = taskWidgetId,
                taskGroupId = widgetTaskGroupId,
                color = 112233
            )
        )
        taskGroupDao.insert(SingleTaskGroupEntity(name = "foobar", id = widgetTaskGroupId))
        val taskGroupIdLinkedToWidget = taskWidgetsDao.getTaskGroupIdByWidgetId(taskWidgetId)

        // then
        assertThat(taskGroupIdLinkedToWidget).isEqualTo(widgetTaskGroupId)
    }
}