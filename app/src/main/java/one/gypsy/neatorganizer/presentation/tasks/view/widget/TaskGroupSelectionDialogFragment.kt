package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.DialogFragmentSelectTaskGroupBinding
import one.gypsy.neatorganizer.presentation.tasks.vm.TaskWidgetSelectionViewModel
import org.koin.core.parameter.parametersOf

class TaskGroupSelectionDialogFragment : BottomSheetDialogFragment() {

    private val args: TaskGroupSelectionDialogFragmentArgs by navArgs()
    private val viewModel: TaskWidgetSelectionViewModel by viewModel {
        parametersOf(args.widgetId)
    }
    lateinit var fragmentBinding: DialogFragmentSelectTaskGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme_Transparent)
        isCancelable = false
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_fragment_select_task_group,
            container,
            false
        )
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.viewModel = viewModel
        fragmentBinding.lifecycleOwner = this
    }


}