package one.gypsy.neatorganizer.presentation

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
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
import one.gypsy.neatorganizer.task.tasksUiModule
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
                tasksUiModule,
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

        registerActivityLifecycleCallbacks(createLifecycleCallbacks())
    }

    private fun createLifecycleCallbacks() = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        override fun onActivityStarted(activity: Activity) {}

        override fun onActivityResumed(activity: Activity) {}

        override fun onActivityPaused(activity: Activity) {}

        override fun onActivityStopped(activity: Activity) {}

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

        override fun onActivityDestroyed(activity: Activity) {}
    }
}
