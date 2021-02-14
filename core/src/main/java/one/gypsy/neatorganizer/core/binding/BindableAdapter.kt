package one.gypsy.neatorganizer.core.binding

interface BindableAdapter<T> {
    fun bindData(dataCollection: List<T>)
}
