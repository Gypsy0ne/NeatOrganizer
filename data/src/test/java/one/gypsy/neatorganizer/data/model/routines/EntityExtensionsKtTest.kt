package one.gypsy.neatorganizer.data.model.routines

import one.gypsy.neatorganizer.database.entity.routines.RoutineEntity
import one.gypsy.neatorganizer.database.entity.routines.RoutineScheduleEntity
import one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity
import one.gypsy.neatorganizer.database.entity.routines.ScheduledRoutineWithTasks
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EntityExtensionsKtTest {

    @Test
    fun shouldProperlyMapRoutineScheduleEntityToDataModel() {
        // given
        val routineSchedule =
            RoutineScheduleEntity(
                monday = true,
                tuesday = false,
                wednesday = true,
                thursday = false,
                friday = true,
                saturday = false,
                sunday = true,
                routineId = 1
            )

        // when
        val dataRoutineSchedule = routineSchedule.toRoutineSchedule()

        // then
        assertThat(routineSchedule.routineId).isEqualTo(dataRoutineSchedule.routineId)
        assertThat(
            routineSchedule.let {
                listOf(
                    it.monday,
                    it.tuesday,
                    it.wednesday,
                    it.thursday,
                    it.friday,
                    it.saturday,
                    it.sunday
                )
            }
        ).isEqualTo(dataRoutineSchedule.scheduledDays)
    }

    @Test
    fun shouldProperlyMapRoutineTaskEntityToDataModel() {
        // given
        val routineTaskEntity = RoutineTaskEntity(
            "foobar",
            true,
            routineId = 1,
            id = 1,
            createdAt = 12344122
        )

        // when
        val dataRoutineTask = routineTaskEntity.toRoutineTaskEntry()

        // then
        assertThat(routineTaskEntity.name).isEqualTo(dataRoutineTask.name)
        assertThat(routineTaskEntity.done).isEqualTo(dataRoutineTask.done)
        assertThat(routineTaskEntity.id).isEqualTo(dataRoutineTask.id)
        assertThat(routineTaskEntity.routineId).isEqualTo(dataRoutineTask.routineId)
        assertThat(routineTaskEntity.createdAt).isEqualTo(dataRoutineTask.createdAt)
    }

    @Test
    fun shouldMapScheduledRoutineWithTasksToDataModelWithEmptySchedule() {
        // given
        val scheduledRoutineId = 1L
        val scheduledRoutine = RoutineEntity(
            "foobar",
            id = scheduledRoutineId,
            createdAt = 123124
        )
        val routineSchedule = null
        val routineTasks = listOf(
            RoutineTaskEntity(
                "taskOne",
                true,
                routineId = scheduledRoutineId,
                id = 1L,
                createdAt = 1124
            ),
            RoutineTaskEntity(
                "taskTwo",
                true,
                routineId = scheduledRoutineId,
                id = 2L,
                createdAt = 123
            )
        )
        val scheduledRoutineWithTasks =
            ScheduledRoutineWithTasks(
                scheduledRoutine,
                routineTasks,
                routineSchedule
            )

        // when
        val dataScheduledRoutineWithTasks = scheduledRoutineWithTasks.toRoutineWithTasks()

        // then
        assertThat(dataScheduledRoutineWithTasks.schedule.scheduledDays).isEqualTo(RoutineSchedule.EMPTY.scheduledDays)
        assertThat(dataScheduledRoutineWithTasks.schedule.routineId).isEqualTo(scheduledRoutineId)
    }

    @Test
    fun shouldMapScheduledRoutineWithTasksToDataModel() {
        // given
        val scheduledRoutineId = 1L
        val scheduledRoutine = RoutineEntity(
            "foobar",
            id = scheduledRoutineId,
            createdAt = 123124
        )
        val routineSchedule =
            RoutineScheduleEntity(
                monday = true,
                tuesday = true,
                wednesday = false,
                thursday = false,
                friday = true,
                saturday = true,
                sunday = true,
                routineId = scheduledRoutineId
            )
        val routineTasks = listOf(
            RoutineTaskEntity(
                "taskOne",
                true,
                routineId = scheduledRoutineId,
                id = 1L,
                createdAt = 1231
            ),
            RoutineTaskEntity(
                "taskTwo",
                true,
                routineId = scheduledRoutineId,
                id = 2L,
                createdAt = 123
            )
        )
        val scheduledRoutineWithTasks =
            ScheduledRoutineWithTasks(
                scheduledRoutine,
                routineTasks,
                routineSchedule
            )

        // when
        val dataScheduledRoutineWithTasks = scheduledRoutineWithTasks.toRoutineWithTasks()

        // then
        assertThat(dataScheduledRoutineWithTasks.id).isEqualTo(scheduledRoutine.id)
        assertThat(dataScheduledRoutineWithTasks.name).isEqualTo(scheduledRoutine.name)
        dataScheduledRoutineWithTasks.schedule.apply {
            assertThat(routineId).isEqualTo(scheduledRoutine.id)
            assertThat(scheduledDays).isEqualTo(
                routineSchedule.let {
                    listOf(
                        it.monday,
                        it.tuesday,
                        it.wednesday,
                        it.thursday,
                        it.friday,
                        it.saturday,
                        it.sunday
                    )
                }
            )
        }
        dataScheduledRoutineWithTasks.tasks.forEachIndexed { index, routineTaskEntry ->
            assertThat(routineTaskEntry.done).isEqualTo(routineTasks[index].done)
            assertThat(routineTaskEntry.id).isEqualTo(routineTasks[index].id)
            assertThat(routineTaskEntry.routineId).isEqualTo(routineTasks[index].routineId)
            assertThat(routineTaskEntry.name).isEqualTo(routineTasks[index].name)
            assertThat(routineTaskEntry.createdAt).isEqualTo(routineTasks[index].createdAt)
        }
    }
}
