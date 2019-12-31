package one.gypsy.neatorganizer.binding

interface BindableAdapter<T> {
    fun setData(dataCollection: List<T>)
}