package one.gypsy.neatorganizer.presentation

import android.app.Application
import one.gypsy.neatorganizer.database.databaseModule
import one.gypsy.neatorganizer.di.notesDataSourceModule
import one.gypsy.neatorganizer.di.notesRepositoryModule
import one.gypsy.neatorganizer.di.notesUseCaseModule
import one.gypsy.neatorganizer.di.notesUtilsModule
import one.gypsy.neatorganizer.di.notesViewModelModule
import one.gypsy.neatorganizer.di.routinesDataSourceModule
import one.gypsy.neatorganizer.di.routinesRepositoryModule
import one.gypsy.neatorganizer.di.routinesResetDataSourceModule
import one.gypsy.neatorganizer.di.routinesResetRepositoryModule
import one.gypsy.neatorganizer.di.routinesResetUtilsModule
import one.gypsy.neatorganizer.di.routinesUseCaseModule
import one.gypsy.neatorganizer.di.routinesUtilsModule
import one.gypsy.neatorganizer.di.routinesViewModelModule
import one.gypsy.neatorganizer.di.serviceModule
import one.gypsy.neatorganizer.di.tasksDataSourceModule
import one.gypsy.neatorganizer.di.tasksRepositoryModule
import one.gypsy.neatorganizer.di.tasksUseCaseModule
import one.gypsy.neatorganizer.di.tasksUtilsModule
import one.gypsy.neatorganizer.di.tasksViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OrganizerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OrganizerApplication)
            modules(
                // Db
                databaseModule,
                // Tasks
                tasksViewModelModule,
                tasksDataSourceModule,
                tasksRepositoryModule,
                tasksUseCaseModule,
                tasksUtilsModule,
                // Routines
                routinesViewModelModule,
                routinesDataSourceModule,
                routinesRepositoryModule,
                routinesUseCaseModule,
                routinesUtilsModule,
                // Routines Reset
                routinesResetDataSourceModule,
                routinesResetRepositoryModule,
                routinesResetUtilsModule,
                // Widget
                serviceModule,
                // Notes
                notesViewModelModule,
                notesDataSourceModule,
                notesRepositoryModule,
                notesUseCaseModule,
                notesUtilsModule
            )
        }
    }
}
