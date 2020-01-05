package one.gypsy.neatorganizer.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import one.gypsy.neatorganizer.OrganizerApplication
import javax.inject.Singleton

@Component(modules = [ActivityModule::class, DataModule::class, FragmentModule::class, DataSourceModule::class, RepositoryModule::class, UseCaseModule::class, ViewModelModule::class, AndroidSupportInjectionModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(organizerApp: OrganizerApplication)
}