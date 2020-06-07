package one.gypsy.neatorganizer.presentation.listing

interface ListedSubItem<T : SubItem> : ListedView<T> {
    fun setUpDoneListener()
}