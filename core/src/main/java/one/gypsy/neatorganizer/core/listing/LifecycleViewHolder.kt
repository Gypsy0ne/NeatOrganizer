package one.gypsy.neatorganizer.core.listing

import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

abstract class LifecycleViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView),
    LifecycleOwner {

    private val lifecycleRegistry = createLifecycleRegistry()

    private fun createLifecycleRegistry() = LifecycleRegistry(this).apply {
        handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    @CallSuper
    open fun onAttached() = lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)

    @CallSuper
    open fun onDetached() = lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)

    @CallSuper
    open fun onRecycled() = lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
}
