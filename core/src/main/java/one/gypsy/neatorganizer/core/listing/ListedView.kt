package one.gypsy.neatorganizer.core.listing

interface ListedView<T> {
    var viewData: T

    fun setUpSwipeMenuBehavior()
    fun clearEditionStatus()
    fun setUpEditListener()
    fun setUpEditionSubmitListener()
    fun setUpRemoveListener()
    fun updateEditable()
}
