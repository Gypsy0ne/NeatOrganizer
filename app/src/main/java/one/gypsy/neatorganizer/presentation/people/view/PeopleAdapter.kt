package one.gypsy.neatorganizer.presentation.people.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.databinding.ItemPersonBinding
import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.presentation.people.vm.PeopleViewModel
import one.gypsy.neatorganizer.presentation.people.vm.PersonEntryViewModel
import javax.inject.Inject

//TODO implement bind/unbind mechanism
class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PersonViewHolder>(), BindableAdapter<List<PersonEntry>> {

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
            //TODO to init VM with provider factory is needed
//            val personViewModel= ViewModelProviders.of(context as FragmentActivity, viewModelFactory)[PersonEntryViewModel::class.java]
            val personViewModel= PersonEntryViewModel().apply { bind(personEntryData) }
            binding.apply {
                viewModel = personViewModel
                profileRedirectClickListener = View.OnClickListener {
                    navigateToPersonHistory(personEntryData, it)
                }
                interactionUpdateClickListener = View.OnClickListener {
//                    personViewModel.onUpdateInteractionClick()
                    this.swipeLayoutItemPersonRoot.resetStatus()
                }
                executePendingBindings()
            }
        }
    }
}