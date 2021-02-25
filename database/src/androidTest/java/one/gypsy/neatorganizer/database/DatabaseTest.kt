package one.gypsy.neatorganizer.domain.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

abstract class DatabaseTest {

    protected lateinit var database: one.gypsy.neatorganizer.database.OrganizerDatabase

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    open fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            one.gypsy.neatorganizer.database.OrganizerDatabase::class.java
        ).build()
    }

    @After
    fun finish() {
        database.close()
    }
}
