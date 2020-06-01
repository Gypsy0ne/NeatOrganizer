package one.gypsy.neatorganizer.presentation.listing

interface HeaderClickListener<T : HeaderItem> {
    fun onExpanderClick(headerItem: T)
    fun onEditionSubmitClick(headerItem: T)
    fun onRemoveClick(headerItem: T)
}