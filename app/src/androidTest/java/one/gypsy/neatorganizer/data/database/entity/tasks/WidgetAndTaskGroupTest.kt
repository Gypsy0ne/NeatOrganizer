package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.TaskWidgetsDao
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class WidgetAndTaskGroupTest {

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
            Assertions.assertThat(it.widget).isEqualToComparingFieldByField(widget)
            Assertions.assertThat(it.singleTaskGroup).isEqualToComparingFieldByField(taskGroup)
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
        Assertions.assertThat(selectedWidgetWithTaskGroup.widget)
            .isEqualToComparingFieldByField(widget)
        Assertions.assertThat(selectedWidgetWithTaskGroup.singleTaskGroup)
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
        Assertions.assertThat(widgetWithTaskGroup.singleTaskGroup.id).isEqualTo(swappedTaskGroupId)
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