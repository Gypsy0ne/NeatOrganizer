package one.gypsy.neatorganizer.core.listing

interface ListedHeader<T : HeaderItem> : ListedView<T> {
    fun setUpExpanderListener()
    fun setUpAddListener()
}
