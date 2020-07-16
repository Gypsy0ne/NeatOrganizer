package one.gypsy.neatorganizer.presentation.people.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.DialogFragmentAddPersonBinding
import one.gypsy.neatorganizer.presentation.people.vm.AddPersonViewModel
import javax.inject.Inject


class AddPersonDialogFragment: BottomSheetDialogFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: AddPersonViewModel

    lateinit var fragmentBinding: DialogFragmentAddPersonBinding

    private var lastImagePickRequestCode: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =  DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_add_person, container, false)
        return fragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[AddPersonViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.viewModel = viewModel
        fragmentBinding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
        setUpObservers()
    }


    private fun setUpObservers() {
        viewModel.selectThumbnailPhoto.observe(viewLifecycleOwner, Observer {
            openPhotoPicker(it)
        })

        viewModel.finishedAdding.observe(viewLifecycleOwner, Observer { finished ->
            if(finished)
                findNavController().popBackStack()
        })
    }

    private fun openPhotoPicker(requestCode: Int) {
        val getImageIntent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        lastImagePickRequestCode = requestCode
        startActivityForResult(getImageIntent, requestCode)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == lastImagePickRequestCode && resultCode == Activity.RESULT_OK) {
            val intentData = data?.data
        if (intentData != null) {
            viewModel.handleIntentPictureData(intentData)
        } else {
//            showToast(requireContext(), R.string.add_person_dialog_fragment_image_pick_error)
        }
    }
    }
}