package one.gypsy.neatorganizer.core.listing

interface ListedSubItem<T : SubItem> : ListedView<T> {
    fun setUpDoneListener()
}
