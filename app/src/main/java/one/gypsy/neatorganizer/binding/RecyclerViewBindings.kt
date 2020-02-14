package one.gypsy.neatorganizer.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapterData")
fun <T> setAdapterData(recyclerView: RecyclerView, dataCollection: T) {
    if(recyclerView.adapter is BindableAdapter<*> && dataCollection != null) {
        (recyclerView.adapter as BindableAdapter<T>).setData(dataCollection)
    }
}
@BindingAdapter(value = ["adapter", "layoutManager", "itemTouchHelperCallback"], requireAll = false)
fun setAdapter(recyclerView: RecyclerView, rvAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, rvLayoutManager: RecyclerView.LayoutManager, itemTouchCallback: ItemTouchHelper.SimpleCallback?) {
    recyclerView.apply {
        adapter = rvAdapter
        layoutManager = rvLayoutManager
        if(itemTouchCallback != null) ItemTouchHelper(itemTouchCallback).attachToRecyclerView(this)
    }
}