package one.gypsy.neatorganizer.binding

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.utils.CollectionUIState
import one.gypsy.neatorganizer.utils.UIState

@BindingAdapter("adapterData")
fun <T> setAdapterData(recyclerView: RecyclerView, dataCollection: T?) {
    if (recyclerView.adapter is BindableAdapter<*> && dataCollection != null) {
        (recyclerView.adapter as BindableAdapter<T>).setData(dataCollection)
    }
}

@BindingAdapter(value = ["adapter", "layoutManager"], requireAll = false)
fun setAdapter(
    recyclerView: RecyclerView,
    rvAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    rvLayoutManager: RecyclerView.LayoutManager
) {
    recyclerView.apply {
        adapter = rvAdapter
        layoutManager = rvLayoutManager
        setHasFixedSize(true)
        setItemViewCacheSize(resources.getInteger(R.integer.default_recycler_view_cache_size))
    }

}

@BindingAdapter("itemInteractionState")
fun setItemInteractionState(recyclerView: RecyclerView, state: CollectionUIState?) {
    when(state) {
        is CollectionUIState.ItemEditionSuccess -> { recyclerView.findViewHolderForAdapterPosition(state.itemPosition)?.itemView?.startAnimation(AnimationUtils.loadAnimation(recyclerView.context, R.anim.item_enlarge))}
        is CollectionUIState.ItemEditionFailure -> { recyclerView.findViewHolderForAdapterPosition(state.itemPosition)?.itemView?.startAnimation(AnimationUtils.loadAnimation(recyclerView.context, R.anim.item_shake))}
    }
}
