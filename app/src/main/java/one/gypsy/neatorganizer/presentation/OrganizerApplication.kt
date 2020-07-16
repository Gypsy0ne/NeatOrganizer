package one.gypsy.neatorganizer.presentation

import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import one.gypsy.neatorganizer.di.AppComponent
import one.gypsy.neatorganizer.di.DaggerAppComponent
import one.gypsy.neatorganizer.di.tasks.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import javax.inject.Inject

class OrganizerApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        // koin
        startKoin {
            androidContext(this@OrganizerApplication)
            modules(
                tasksViewModelModule,
                tasksDataSourceModule,
                tasksRepositoryModule,
                tasksUseCaseModule,
                databaseModule,
                tasksUtilsModule
            )
        }
    }
}

val Fragment.injector get() = (this.activity?.application as OrganizerApplication).component
