package one.gypsy.neatorganizer.presentation.profile

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.android.support.AndroidSupportInjection
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentPersonProfileBinding
import one.gypsy.neatorganizer.presentation.injector
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity


class PersonProfileFragment: Fragment() {

    private val args: PersonProfileFragmentArgs by navArgs()

    private val personHistoryViewModel by lazy {
        injector.personProfileViewModelFactory.create(
            args.personId
        )
    }

    private lateinit var fragmentBinding: FragmentPersonProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_profile, container, false)
        return fragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.person_profile_app_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.call_person -> {
                startCallIntent()
            }
            R.id.send_email -> {
                startSendEmailIntent()
            }
            else -> false
        }
    }

    private fun startCallIntent(): Boolean {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "123123123"))
        startActivity(intent)
        return true
    }

    private fun startSendEmailIntent(): Boolean {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "emailaddress@emailaddress.com"))
        startActivity(Intent.createChooser(intent, "Send Email"))
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.viewModel = personHistoryViewModel
        fragmentBinding.lifecycleOwner = this
    }

}