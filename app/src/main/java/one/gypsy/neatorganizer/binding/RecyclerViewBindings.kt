package one.gypsy.neatorganizer.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.R

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
