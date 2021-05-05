package one.gypsy.tutorial

import android.view.View
import androidx.fragment.app.Fragment

interface FeatureShowcase {
    fun show(
        fragment: Fragment,
        showcaseTargetView: View,
        showOnce: Boolean
    )
}
