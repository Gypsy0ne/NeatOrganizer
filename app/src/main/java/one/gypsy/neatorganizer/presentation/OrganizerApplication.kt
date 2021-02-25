package one.gypsy.neatorganizer.presentation

import android.app.Application
import one.gypsy.neatorganizer.data.notesDataSourceModule
import one.gypsy.neatorganizer.data.routinesDataSourceModule
import one.gypsy.neatorganizer.data.routinesResetDataSourceModule
import one.gypsy.neatorganizer.data.tasksDataSourceModule
import one.gypsy.neatorganizer.database.databaseModule
import one.gypsy.neatorganizer.domain.notesRepositoryModule
import one.gypsy.neatorganizer.domain.notesUseCaseModule
import one.gypsy.neatorganizer.domain.routinesRepositoryModule
import one.gypsy.neatorganizer.domain.routinesResetRepositoryModule
import one.gypsy.neatorganizer.domain.routinesResetUtilsModule
import one.gypsy.neatorganizer.domain.routinesUseCaseModule
import one.gypsy.neatorganizer.domain.tasksRepositoryModule
import one.gypsy.neatorganizer.domain.tasksUseCaseModule
import one.gypsy.neatorganizer.note.noteUtilsModule
import one.gypsy.neatorganizer.note.noteViewModelModule
import one.gypsy.neatorganizer.routine.routineUtilsModule
import one.gypsy.neatorganizer.routine.routineViewModelModule
import one.gypsy.neatorganizer.task.taskUtilsModule
import one.gypsy.neatorganizer.task.taskViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class OrganizerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OrganizerApplication)
            modules(
                databaseModule,
                // task
                taskUtilsModule,
                taskViewModelModule,
                // routine
                routineViewModelModule,
                routineUtilsModule,
                // data
                notesDataSourceModule,
                routinesDataSourceModule,
                routinesResetDataSourceModule,
                tasksDataSourceModule,
                // domain
                notesRepositoryModule,
                notesUseCaseModule,
                routinesUseCaseModule,
                routinesRepositoryModule,
                tasksUseCaseModule,
                tasksRepositoryModule,
                routinesResetRepositoryModule,
                routinesResetUtilsModule,
                // note
                noteUtilsModule,
                noteViewModelModule,
            )
        }
    }
}
