package one.gypsy.neatorganizer.presentation.di

import android.app.Application
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import one.gypsy.neatorganizer.presentation.OrganizerApplication
import one.gypsy.neatorganizer.presentation.people.vm.RateInteractionViewModel
import one.gypsy.neatorganizer.presentation.profile.vm.PersonProfileViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskViewModel
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

    val personProfileViewModelFactory: PersonProfileViewModel.Factory
    val rateInteractionViewModelFactory: RateInteractionViewModel.Factory
    val addTaskViewModelFactory: AddTaskViewModel.Factory

}

@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
interface AssistedInjectModule