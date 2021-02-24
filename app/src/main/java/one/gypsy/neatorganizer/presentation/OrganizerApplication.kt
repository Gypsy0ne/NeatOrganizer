package one.gypsy.neatorganizer.presentation

import android.app.Application
import one.gypsy.neatorganizer.data.dataModule
import one.gypsy.neatorganizer.database.databaseModule
import one.gypsy.neatorganizer.domain.domainModule
import one.gypsy.neatorganizer.note.noteModule
import one.gypsy.neatorganizer.routine.routineModule
import one.gypsy.neatorganizer.task.taskModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OrganizerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OrganizerApplication)
            modules(
                databaseModule,
                taskModule,
                routineModule,
                dataModule,
                domainModule,
                noteModule
            )
        }
    }
}
