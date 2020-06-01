package one.gypsy.neatorganizer.presentation.people.view

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.databinding.ItemPersonBinding
import one.gypsy.neatorganizer.domain.dto.people.PersonEntry
import one.gypsy.neatorganizer.presentation.people.vm.PersonEntryViewModel

class PersonViewHolder(private val binding: ItemPersonBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private fun navigateToPersonProfile(
        personId: Long,
        view: View
    ) {
        val direction =
            PeopleFragmentDirections.actionPeopleToPersonProfileFragment(
                personId
            )
        view.findNavController().navigate(direction)
    }

    private fun navigateToRateInteraction(
        personId: Long,
        view: View
    ) {
        val direction =
            PeopleFragmentDirections.actionPeopleToRateInteractionDialogFragment(personId)
        view.findNavController().navigate(direction)
    }

    fun bind(personEntryData: PersonEntry) {
        //TODO to init VM with provider factory is needed
        val personViewModel = PersonEntryViewModel().apply { bind(personEntryData) }
        binding.apply {
            viewModel = personViewModel
            profileRedirectClickListener = View.OnClickListener {
                navigateToPersonProfile(personEntryData.id, it)
            }
            interactionUpdateClickListener = View.OnClickListener {
                navigateToRateInteraction(personEntryData.id, it)
                this.swipeLayoutItemPersonRoot.resetStatus()
            }
            executePendingBindings()
        }
    }
}