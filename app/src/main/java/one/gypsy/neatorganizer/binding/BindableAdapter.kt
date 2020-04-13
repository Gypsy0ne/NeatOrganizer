package one.gypsy.neatorganizer.binding

interface BindableAdapter<T> {
    fun bindData(dataCollection: List<T>)
}
