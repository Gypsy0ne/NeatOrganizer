package one.gypsy.neatorganizer.presentation.listing

interface SubItemClickListener<T : SubItem> {
    fun onDoneClick(subItem: T)
    fun onEditionSubmitClick(subItem: T)
    fun onRemoveClick(subItem: T)
}