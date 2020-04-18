package one.gypsy.neatorganizer.presentation.tasks.view

import android.content.Context
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
import one.gypsy.neatorganizer.databinding.DialogFragmentAddTaskGroupBinding
import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskGroupViewModel
import javax.inject.Inject

class AddSingleTaskGroupDialogFragment : BottomSheetDialogFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: AddTaskGroupViewModel

    lateinit var fragmentBinding: DialogFragmentAddTaskGroupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_fragment_add_task_group,
            container,
            false
        )
        return fragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[AddTaskGroupViewModel::class.java]
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
        viewModel.finishedAdding.observe(viewLifecycleOwner, Observer { finished ->
            if (finished)
                findNavController().popBackStack()
        })
    }
}