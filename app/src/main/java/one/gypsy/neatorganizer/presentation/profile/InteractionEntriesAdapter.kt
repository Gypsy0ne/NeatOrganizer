package one.gypsy.neatorganizer.presentation.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.databinding.ItemInteractionBinding
import one.gypsy.neatorganizer.databinding.ItemPersonBinding
import one.gypsy.neatorganizer.domain.dto.InteractionEntry
import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.presentation.people.vm.PersonEntryViewModel
import one.gypsy.neatorganizer.presentation.profile.vm.InteractionEntryViewModel

//TODO implement bind/unbind mechanism
class InteractionEntriesAdapter : RecyclerView.Adapter<InteractionEntriesAdapter.InteractionEntryViewHolder>(), BindableAdapter<List<InteractionEntry>> {

    private var entries = mutableListOf<InteractionEntry>()

    //TODO adjust this method to work with diff util and search widget
    override fun setData(dataCollection: List<InteractionEntry>) {
        entries.apply {
            clear()
            addAll(dataCollection)
            reverse()
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InteractionEntryViewHolder {
        val interactionItemBinding: ItemInteractionBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_interaction, parent, false)
        return InteractionEntryViewHolder(interactionItemBinding)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: InteractionEntryViewHolder, position: Int) {
        holder.bind(entries[position])
    }

    inner class InteractionEntryViewHolder(private val binding: ItemInteractionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(entry: InteractionEntry) {
            val interactionViewModel= InteractionEntryViewModel().apply { bind(entry) }
            binding.apply {
                viewModel = interactionViewModel
                executePendingBindings()
            }
        }
    }
}