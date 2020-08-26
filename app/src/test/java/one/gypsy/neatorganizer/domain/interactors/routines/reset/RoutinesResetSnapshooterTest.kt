package one.gypsy.neatorganizer.domain.interactors.routines.reset

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import one.gypsy.neatorganizer.data.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.data.repositories.routines.RoutinesRepository
import one.gypsy.neatorganizer.data.repositories.routines.reset.RoutineSnapshotsRepository
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RoutinesResetSnapshooterTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val routinesRepository = mockk<RoutinesRepository>(relaxed = true)
    private val routineSnapshotsRepository = mockk<RoutineSnapshotsRepository>(relaxed = true)
    private val routineTasksRepository = mockk<RoutineTasksRepository>(relaxed = true)
    private val routinesResetSnapshooter = RoutinesResetSnapshooter(
        routinesRepository,
        routineSnapshotsRepository,
        routineTasksRepository
    )

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `should properly cos tam`() {
        // given
        coEvery { routinesRepository.getAllRoutines() } returns routines

        // when
        runBlockingTest {
            routinesResetSnapshooter.performWeeklyRoutinesReset()
        }
    }

    companion object {
        val routineSchedule1 =
            RoutineSchedule(1, listOf(false, true, false, true, true, false, true))
        val routineSchedule2 =
            RoutineSchedule(2, listOf(true, true, true, true, true, false, false))
        val tasks1 = listOf(
            RoutineTaskEntry(1, 1, "task1", false),
            RoutineTaskEntry(2, 1, "task2", false),
            RoutineTaskEntry(3, 1, "task3", true),
            RoutineTaskEntry(4, 1, "task4", true),
            RoutineTaskEntry(5, 1, "task5", true)
        )
        val tasks2 = listOf(
            RoutineTaskEntry(6, 2, "task1", false),
            RoutineTaskEntry(7, 2, "task2", true),
            RoutineTaskEntry(8, 2, "task3", false),
            RoutineTaskEntry(9, 2, "task4", true),
            RoutineTaskEntry(10, 2, "task5", true)
        )
        val routines = listOf(
            Routine(1, "rutyna1", routineSchedule1, tasks1),
            Routine(2, "rutyna2", routineSchedule2, tasks2)
        )
    }
}