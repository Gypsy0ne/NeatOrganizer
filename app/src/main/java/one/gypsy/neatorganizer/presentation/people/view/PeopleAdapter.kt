package one.gypsy.neatorganizer.presentation.people.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.databinding.ItemPersonBinding
import one.gypsy.neatorganizer.domain.Person
import one.gypsy.neatorganizer.presentation.people.vm.PersonViewModel

//TODO implement bind/unbind mechanism
class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PersonViewHolder>(), BindableAdapter<List<Person>> {

    private var people = mutableListOf<Person>()

    //TODO adjust this method to work with diff util and search widget
    override fun setData(dataCollection: List<Person>) {
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

    inner class PersonViewHolder(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {

        private fun navigateToPersonHistory(
            person: Person,
            view: View
        ) {
            val direction =
                PeopleFragmentDirections.actionPeopleToPersonProfileFragment(
                    person.id
                )
            view.findNavController().navigate(direction)
        }

        fun bind(personData: Person) {
            val personViewModel= PersonViewModel().apply { bind(personData) }
            binding.apply {
                viewModel = personViewModel
                setClickListener {
                    navigateToPersonHistory(personData, it)
                }
                executePendingBindings()
            }
        }
    }
}