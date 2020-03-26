package one.gypsy.neatorganizer.presentation

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import one.gypsy.neatorganizer.presentation.di.AppComponent
import one.gypsy.neatorganizer.presentation.di.DaggerAppComponent
import javax.inject.Inject

class OrganizerApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}

val Fragment.injector get() = (this.activity?.application as OrganizerApplication).component
