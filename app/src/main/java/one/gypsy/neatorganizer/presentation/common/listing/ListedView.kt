package one.gypsy.neatorganizer.presentation.common.listing

interface ListedView<T : Listed> {
    var viewData: T

    fun setUpSwipeMenuBehavior()
    fun clearEditionStatus()
    fun setUpEditListener()
    fun setUpEditionSubmitListener()
    fun setUpRemoveListener()
    fun updateEditable()
}
