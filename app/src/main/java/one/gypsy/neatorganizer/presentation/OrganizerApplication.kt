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
                tasksViewModelModule,
                tasksDataSourceModule,
                tasksRepositoryModule,
                tasksUseCaseModule,
                databaseModule,
                tasksUtilsModule,
                routinesViewModelModule,
                routinesDataSourceModule,
                routinesRepositoryModule,
                routinesUseCaseModule,
                routinesUtilsModule,
                peopleDataSourceModule,
                peopleRepositoryModule,
                peopleUseCaseModule,
                peopleViewModelModule,
                profileUseCaseModule,
                profileViewModelModule
            )
        }
    }
}
