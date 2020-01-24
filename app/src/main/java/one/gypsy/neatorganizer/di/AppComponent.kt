package one.gypsy.neatorganizer.di

import android.app.Application
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import one.gypsy.neatorganizer.OrganizerApplication
import one.gypsy.neatorganizer.screens.people.vm.PersonHistoryViewModel
import javax.inject.Singleton

@Component(modules = [AssistedInjectModule::class, ActivityModule::class, DataModule::class, FragmentModule::class, DataSourceModule::class, RepositoryModule::class, UseCaseModule::class, ViewModelModule::class, AndroidSupportInjectionModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(organizerApp: OrganizerApplication)

    val personHistoryViewModelFactory: PersonHistoryViewModel.Factory

}
@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
interface AssistedInjectModule