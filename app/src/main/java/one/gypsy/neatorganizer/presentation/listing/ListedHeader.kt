package one.gypsy.neatorganizer.presentation.listing

interface ListedHeader<T : HeaderItem> : ListedView<T> {
    fun setUpExpanderListener()
    fun setUpAddListener()
}