package one.gypsy.neatorganizer.presentation.people.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.databinding.ItemPersonBinding
import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.presentation.people.vm.PersonViewModel

//TODO implement bind/unbind mechanism
class PeopleAdapter(val context: Context) : RecyclerView.Adapter<PeopleAdapter.PersonViewHolder>(), BindableAdapter<List<PersonEntry>> {

    private var people = mutableListOf<PersonEntry>()

    //TODO adjust this method to work with diff util and search widget
    override fun setData(dataCollection: List<PersonEntry>) {
        people.clear()
        people.addAll(dataCollection)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val personItemBinding: ItemPersonBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_person, parent, false)
        return PersonViewHolder(personItemBinding)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(people[position])
    }

    fun addInteractionEntry(itemPosition: Int) {

    }

    inner class PersonViewHolder(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {

        private fun navigateToPersonHistory(
            personEntry: PersonEntry,
            view: View
        ) {
            val direction =
                PeopleFragmentDirections.actionPeopleToPersonProfileFragment(
                    personEntry.id
                )
            view.findNavController().navigate(direction)
        }

        fun bind(personEntryData: PersonEntry) {
            val personViewModel= PersonViewModel().apply { bind(personEntryData) }
            binding.apply {
                viewModel = personViewModel
                setClickListener {
                    navigateToPersonHistory(personEntryData, it)
                }
                executePendingBindings()
            }
        }
    }
}