package one.gypsy.tutorial

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.shape.FocusGravity
import com.codertainment.materialintro.shape.ShapeType
import com.codertainment.materialintro.utils.materialIntro
import com.codertainment.materialintro.view.MaterialIntroView
import one.gypsy.tutorial.databinding.SwipeShowcaseBinding

class SwipeShowcase : FeatureShowcase {

    override fun show(
        fragment: Fragment,
        showcaseTargetView: View,
        showOnce: Boolean,
    ) {
        fragment.materialIntro(
            show = true,
            func = getConfiguration(
                showcaseTargetView = showcaseTargetView,
                showOnce = showOnce
            )
        )
    }

    private fun getConfiguration(
        showcaseTargetView: View,
        showOnce: Boolean
    ): (MaterialIntroView.() -> Unit) = {
        focusType = Focus.MINIMUM
        shapeType = ShapeType.RECTANGLE
        isFadeInAnimationEnabled = true
        isFadeOutAnimationEnabled = true
        fadeAnimationDurationMillis = 300
        focusGravity = FocusGravity.CENTER
        dismissOnTouch = false
        infoCardBackgroundColor = Color.WHITE
        infoCustomView = inflateInfoView()
        isDotViewEnabled = false
        targetView = showcaseTargetView
        showOnlyOnce = showOnce
        userClickAsDisplayed = true
    }

    private fun MaterialIntroView.inflateInfoView() =
        SwipeShowcaseBinding.inflate(LayoutInflater.from(this.context), this, false).apply {
            finishButton.setOnClickListener {
                this@inflateInfoView.dismiss()
            }
        }.root
}
