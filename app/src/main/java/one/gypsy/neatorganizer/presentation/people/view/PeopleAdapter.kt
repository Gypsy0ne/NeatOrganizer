package one.gypsy.neatorganizer.presentation.people.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.databinding.ItemPersonBinding
import one.gypsy.neatorganizer.domain.dto.people.PersonEntry

//TODO implement bind/unbind mechanism
class PeopleAdapter : RecyclerView.Adapter<PersonViewHolder>(), BindableAdapter<PersonEntry> {

    private var people = mutableListOf<PersonEntry>()

//    var itemInteractionListener: PeopleFragment.PeopleInteractionListener? = null

    //TODO adjust this method to work with diff util and search widget
    override fun bindData(dataCollection: List<PersonEntry>) {
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
}