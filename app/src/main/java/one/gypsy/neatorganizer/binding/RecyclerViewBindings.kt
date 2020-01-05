package one.gypsy.neatorganizer.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.screens.people.view.PeopleAdapter

@BindingAdapter("linearAdapter")
fun setLinearAdapter(recyclerView: RecyclerView, sizeFixed: Boolean) {
//    recyclerView.apply {
//        layoutManager = LinearLayoutManager(recyclerView.context)
//        setHasFixedSize(sizeFixed)
//        adapter = PeopleAdapter()
//    }
}

@BindingAdapter("adapterData")
fun <T> setAdapterData(recyclerView: RecyclerView, dataCollection: List<T>) {
    if(recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(dataCollection)
    }
}