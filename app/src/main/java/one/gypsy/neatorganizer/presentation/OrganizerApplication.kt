package one.gypsy.neatorganizer.presentation

import android.app.Application
import one.gypsy.neatorganizer.di.*
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
