package one.gypsy.neatorganizer.presentation.routines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.presentation.SectionFragment

class RoutinesFragment : SectionFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_routines, container, false)
    }

}