package one.gypsy.neatorganizer.binding

import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.utils.CollectionUIState

@BindingAdapter("adapterData")
fun <T> setAdapterData(recyclerView: RecyclerView, dataCollection: List<T>?) {
    if (recyclerView.adapter is BindableAdapter<*> && dataCollection != null) {
        (recyclerView.adapter as BindableAdapter<T>).bindData(dataCollection)
    }
}

@BindingAdapter(value = ["adapter", "layoutManager", "hasFixedSize"], requireAll = false)
fun setAdapter(
    recyclerView: RecyclerView,
    rvAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    rvLayoutManager: RecyclerView.LayoutManager,
    fixedSize: Boolean
) {
    recyclerView.apply {
        adapter = rvAdapter
        layoutManager = rvLayoutManager
        setHasFixedSize(fixedSize)
        setItemViewCacheSize(resources.getInteger(R.integer.default_recycler_view_cache_size))
    }
}



@BindingAdapter("itemInteractionState")
fun setItemInteractionState(recyclerView: RecyclerView, state: CollectionUIState?) {
    if (state != null) {
        recyclerView.findViewHolderForAdapterPosition(state.itemPosition)?.itemView?.startAnimation(
            when (state) {
                is CollectionUIState.ItemEditionSuccess -> {
                    AnimationUtils.loadAnimation(recyclerView.context, R.anim.item_enlarge)
                }
                is CollectionUIState.ItemEditionFailure -> {
                    AnimationUtils.loadAnimation(recyclerView.context, R.anim.item_enlarge)
                }
            }
        )
    }
}



